package hu.webler.bootstrap;

import hu.webler.model.Author;
import hu.webler.model.Book;
import hu.webler.value.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DataLoaderTripleAMockTest {

    @Mock
    DataLoader mockedDataLoader;

    @BeforeEach
    public void setUp() {
        mockedDataLoader = Mockito.mock(DataLoader.class);
    }

    @Test
    @DisplayName("Using mock and triple A")
    public void testLoadDataReturnsExpectedBooks() {
        // arrange - A
        Author author1 = new Author("John Doe");
        Author author2 = new Author("Jane Smith");
        Book book1 = new Book("Book 1", author1, Category.FICTION);
        Book book2 = new Book("Book 2", author2, Category.NON_FICTION);
        Book book3 = new Book("Book 3", author1, Category.SCIENCE_FICTION);
        List<Book> mockBooks = Arrays.asList(book1, book2, book3);

        when(mockedDataLoader.loadData()).thenReturn(mockBooks);

        // act - A
        // invoke the method under test
        List<Book> result = mockedDataLoader.loadData();

        // assert -> verify the result - A
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals("Book 2", result.get(1).getTitle());
        assertEquals("John Doe", result.get(2).getAuthor().getName());
        // assertTrue("John Doe".equals(result.get(2).getAuthor().getName())); // nem annyira szép, helyette az assertEquals ajánlott
        assertEquals(Category.SCIENCE_FICTION, result.get(2).getCategory());
        assertEquals("SCIENCE_FICTION", result.get(2).getCategory().name());
    }
}
