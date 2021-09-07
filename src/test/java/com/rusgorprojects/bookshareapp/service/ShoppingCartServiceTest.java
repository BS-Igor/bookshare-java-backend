package com.rusgorprojects.bookshareapp.service;

import com.rusgorprojects.bookshareapp.exceptions.IdNotFoundException;
import com.rusgorprojects.bookshareapp.model.BookCreateRequest;
import com.rusgorprojects.bookshareapp.model.BookResponse;
import com.rusgorprojects.bookshareapp.model.OrderPositionResponse;
import com.rusgorprojects.bookshareapp.repository.BookRepository;
import com.rusgorprojects.bookshareapp.repository.OrderPositionRepository;
import com.rusgorprojects.bookshareapp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartServiceTest {

    private BookRepository bookRepository;
    ShoppingCartService service;

        @BeforeEach
        public void setupTests(){

            bookRepository = new BookRepository();
            service = new ShoppingCartService(
                    new OrderRepository(),
                    new OrderPositionRepository(),
                    bookRepository
            );

        }
    @Test
    public void testThat_calculateSumForEmptyCart_returnsDeliveryCost(){
        // given

        // when
        long result = service.calculateSumForCart(
                new ArrayList<OrderPositionResponse>(),
                500
        );

        // then
        assertThat(result).isEqualTo(500);
        // Assertion Library = assertJ (ist mehr verbreitet)
        // mockito ist in der Library
    }
    @Test
    public void testThat_calculateSumWithOneBook_sumsPriceOfBook(){
        // given
        BookResponse savedBook = saveBook(1000);

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition( orderPositions,savedBook,1);

        // when
        Long result = service.calculateSumForCart(orderPositions, 500);

        // then
        assertThat(result).isEqualTo(1500);
    }

    @Test
    public void testThat_calculateSumWithTwoBooks_sumsPricesOfBook(){
        // given
        BookResponse savedBook1 = saveBook(1000);
        BookResponse savedBook2 = saveBook(2000);

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition( orderPositions,savedBook1,1);
        addOrderPosition( orderPositions,savedBook2,4);

        // when
        Long result = service.calculateSumForCart(orderPositions, 500);

        // then
        assertThat(result).isEqualTo(9500);
    }

    @Test
    public void testThat_calculateSumWithNegativeQuantity_throwsException(){
        // given
        BookResponse savedBook1 = saveBook(1000);
        BookResponse savedBook2 = saveBook(2000);

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition( orderPositions,savedBook1,1);
        addOrderPosition( orderPositions,savedBook2,-4);

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            service.calculateSumForCart(orderPositions, 500);
        });

    }

    @Test
    public void testThat_calculateSumWithNotExistingBook_throwsException(){
        // given
        BookResponse notSavedBook = new BookResponse("","","","","",1000, new ArrayList<>());

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition( orderPositions, notSavedBook,1);

        // then
        assertThrows(IdNotFoundException.class, () -> {
            // when
            service.calculateSumForCart(orderPositions, 500);
        });
    }

    private void addOrderPosition(List<OrderPositionResponse> orderPosition, BookResponse savedBook,  int quantity) {
        orderPosition.add(
                new OrderPositionResponse(
                        "1",
                        "order-id",
                        savedBook.getId(),
                        quantity
                )
        );
    }

    private BookResponse saveBook(int price) {
        BookResponse savedBook = bookRepository.save(
                new BookCreateRequest(
                        "Neues Buch",
                        "Beschreibung",
                        "Ein Author",
                        "isbrn-681894 68181",
                        price,
                        new ArrayList<>())
        );
        return savedBook;
    }

}
