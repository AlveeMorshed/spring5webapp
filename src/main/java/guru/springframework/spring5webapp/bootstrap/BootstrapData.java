package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component //Tells Spring that it is a spring managed component
//The Overriden Run method gets executed
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);




        Author eric = new Author("Eric", "Evans");
        Book bk = new Book("Domain Driven Design", "321321");
        eric.getBooks().add(bk);
        bk.getAuthors().add(eric);

        bk.setPublisher(publisher);
        publisher.getBooks().add(bk);

        authorRepository.save(eric);
        bookRepository.save(bk);
        //publisherRepository.save(publisher);
//Saving data using Repository methods.
//Spring Data JPA is utilizing Hibernate to store these
// data in an in-memory H2 database
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "1596169156");
        rod.getBooks().add(noEJB);
//        publisherRepository.save(publisher);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: "+bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
        System.out.println("Publisher's number of books: "+publisher.getBooks().size());
    }
}
