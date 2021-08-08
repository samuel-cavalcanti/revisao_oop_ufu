# Uso de APIs para Banco de dados e Junit

## Refatorações

Com o objetivo de refatorar o código da primeira atividade
[revisão oop](Revisao_oop.md), adicionar um banco de dados simples e fazer uma cobertura mínima de testes. Apliquei a
técnica da inversão da dependência nós formulários em relação na página. Ná primeria versão:

```java
public class AddBookPageSwing extends javax.swing.JFrame {
    // em AddReviewPageSwing muda o valor para Revistas
    public static final String pageName = "Livros";

    private final BookSwingForm bookForm;
    final MenuButtonsSwing menuButtons;

    final LibraryDataBase database;

    public AddBookPageSwing() {
        setTitle(APP_NAME);
        setMinimumSize(new Dimension(MIN_SIZE_IN_PIXELS * 2, MIN_SIZE_IN_PIXELS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        database = LibraryDataBase.getInstance(); // Singleton
        bookForm = new BookSwingForm(); // vai virar abstrato
        menuButtons = new MenuButtonsSwing(pageName);
```

Para resolver esse problema, foi criado a [__SwingForm__](src/main/java/revisao_oop/formPage/swingForm/SwingForm.java)

```java
public abstract class SwingForm extends JComponent {

    public abstract void submit();
}
```

dessa forma não é mais necessário criar um __JFrame__ para cada formulário, pois agora o formulário que depende da __
JFrame__:

```java
public class FormPageSwing extends javax.swing.JFrame {

    private final SwingForm form;
    private final MenuButtonsSwing menuButtons;
    private final String pageName;

    public FormPageSwing(SwingForm form, String pageName) {
        this.pageName = pageName;
        this.form = form;
        ....
```

Uma vez refatorado o formulário da página, foi aplicado o desing Model View Controller (MVC), no formulário, onde o
Model são as estrutas: [Book](src/main/java/revisao_oop/models/Book.java)
e [Review](src/main/java/revisao_oop/models/Review.java). Isso remove completamente as regras de negócio da view.

Também foi refatorado o LibraryDataBase, onde foi extraído uma interface:

```java
public interface LibraryRepository {
    void add(@NotNull Book b);

    void add(@NotNull Review r);

    @NotNull
    List<Book> getBooks();

    @NotNull
    List<Review> getReviews();
}
```

e um módulo chamado __InputValidation__ que até o momento é responsável por higienizar o input não confiável do usuário.
Idealmente esse modulo deveria verificar além dos campos, estiverem vazios, se existe algum tipo de __sql injection__,
mas no momento só verifica se os campos estão vazios.

## Banco de dados

Não tem como Desvincular a escolha do banco do gosto pessoal, particularmente, prefiro fazer a persistência dos dados
com o [__SQlite3__](https://www.sqlitetutorial.net/). SQlite 3, junta a simplicidade de armazenar num único arquivo com
a velocidade de um banco SQL. Quanto a sua implementação, foi utilizado o JDBC. Para popular banco foi criado um
simples [script sql](create_db.sql)
que cria duas tabelas: Books e Reviews, insere 3 itens para
cada tabela e caso já exista essas tabelas, ele exclui ambas.
Para executar o script:
```shell
chmod +x reset_db.sh
./reset_db.sh
```
O script assume que você possui o SQlite3.

## Testes Unitários Automatizados

Se observarmos o enunciado da questão da revisão vemos
que a principal regra de negócio do sistema é a inserção
de dados e a recuperação desses dados, logo
foi feito duas suites de testes unitários, um demonstrado
o caminho feliz e triste da validação dos inputs do usuário
e outro fazendo o teste da recuperação e inserção de dados
no banco.

### teste unitário da validação do input do usuário
```java
class InputValidationTest {


    @Test
    void testBook() {
        Book b = new Book("title", "author", "1996");
        Book b1 = new Book("title", "author", "1996");
        Book validatedBook = new BookValidation().validate(b);
        assertTrue(validatedBook.mEquals(b1));
        assertThrows(IncorrectInputException.class, new Executable() {
            @Override
            public void execute() {
                Book b2 = new Book("", b.author, b.year);
                new BookValidation().validate(b2);
            }
        });

        String expected = "Livro: " + validatedBook.title + " " + validatedBook.author + " " + validatedBook.year;

        assertEquals(expected, validatedBook.toPrettyString());

    }

    @Test
    void testReview() {
        Review r = new Review("title", "org", "vol", "666", "1999");
        Review r1 = new Review("title", "org", "vol", "666", "1999");
        assertTrue(r.mEquals(r1));

        Review validatedReview = new ReviewValidation().validate(r);
        assertTrue(validatedReview.mEquals(r));

        assertThrows(IncorrectInputException.class, new Executable() {
            @Override
            public void execute() {
                Review r = new Review("", "org", "vol", "666", "1999");
                new ReviewValidation().validate(r);
            }
        });

        String expected = "Revista: " + r.title + " " + r.organization + " " + r.volume + " " + r.number + " " + r.year;

        assertEquals(expected, r.toPrettyString());
    }
}
```

### teste unitário do funcionamento da inserção e recuperação de dados

```java
class LibrarySqliteRepositoryTest {

    @Test
    void testSqlRepository() {
        LibraryRepository sqlRepo = LibrarySqliteRepository.getInstance();

        List<Book> books = sqlRepo.getBooks();
        List<Review> reviews = sqlRepo.getReviews();

        assertFalse(books.isEmpty());
        assertFalse(reviews.isEmpty());

        int booksSize = books.size();
        int reviewsSize = reviews.size();

        Book test = new Book("test", "junit", "2020");
        Review testReview = new Review("test", "JUNIT USER GUILDE", "5", "8.7.2", "2020");
        sqlRepo.add(test);
        sqlRepo.add(testReview);

        List<Book> newBooks = sqlRepo.getBooks();
        List<Review> newReviews = sqlRepo.getReviews();
        assertEquals(booksSize + 1, newBooks.size());
        assertEquals(reviewsSize + 1, newReviews.size());


    }

}
```
Para rodar os testes:
```shell
./gradlew test 
```

Também foi pensado em testar os controllers dos formularios,
mas como eles quasem não possuem código, não é necessário
demonstrar o seu funcionamento.

```java
//ReviewFromController.java
public class ReviewFormController implements FormController<Review> {

    private final LibraryRepository dataBase;

    public ReviewFormController(LibraryRepository db) {
        dataBase = db;
    }

    @Override
    public void sendModelToDataBase(@NotNull Review r) {
        dataBase.add(r);
    }
}
// BookFormController.java
public class BookFormController implements FormController<Book> {
    private final LibraryRepository database;

    public BookFormController(LibraryRepository database) {
        this.database = database;
    }

    @Override
    public void sendModelToDataBase(@NotNull Book b) {
        database.add(b);
    }
}
```
Observe que usamos os decorators __@NotNull__ de uma lib mantida
pela Intellij que garante que o parâmetro não possa ser nulo,
caso for percebido que o valor pode ser nulo, o código não
Compila. O que reduz ainda mais o que testar. Novamente
nos deparamos com uma duplicação de código onde julguei que não
devo tentar resolver.

## Referências
Para entender como cheguei nessas modificações, seja legal
dar uma lida no trabalho inicial [Revisão OOP](Revisao_oop.md)