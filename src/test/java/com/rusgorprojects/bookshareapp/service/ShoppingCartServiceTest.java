package com.rusgorprojects.bookshareapp.service;

import com.rusgorprojects.bookshareapp.exceptions.IdNotFoundException;
import com.rusgorprojects.bookshareapp.model.*;
import com.rusgorprojects.bookshareapp.repository.BookRepository;
import com.rusgorprojects.bookshareapp.repository.OrderPositionRepository;
import com.rusgorprojects.bookshareapp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ShoppingCartServiceTest {

    private BookRepository bookRepository;
    private ShoppingCartService service;

    class OrderRepositoryMock extends OrderRepository{
    //save() und findById() überschreiben
        public OrderResponse save(OrderCreateRequest request){
            return null;
        }
    }
        @BeforeEach
        public void setupTests(){

        bookRepository = mock(BookRepository.class);
            service = new ShoppingCartService(
                    mock(OrderRepository.class),
                    mock(OrderPositionRepository.class),
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
        BookResponse savedBook = new BookResponse(
                UUID.randomUUID().toString(),
                "Neues Buch",
                "Beschreibung",
                "Ein Author",
                "isbrn-681894 68181",
                1000,
                new ArrayList<>()
        );

        //Definition des Verhaltens des Mocks
        given(bookRepository.findById(savedBook.getId())).willReturn(Optional.of(savedBook));

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
        BookResponse savedBook1 = new BookResponse(
                UUID.randomUUID().toString(),
                "Neues Buch",
                "Beschreibung",
                "Ein Author",
                "isbrn-681894 68181",
                1000,
                new ArrayList<>()
        );
        //Definition des Verhaltens des Mocks
        given(bookRepository.findById(savedBook1.getId())).willReturn(Optional.of(savedBook1));

        BookResponse savedBook2 = new BookResponse(
                UUID.randomUUID().toString(),
                "Neues Buch",
                "Beschreibung",
                "Ein Author",
                "isbrn-681894 68181",
                2000,
                new ArrayList<>()
        );
        //Definition des Verhaltens des Mocks
        given(bookRepository.findById(savedBook2.getId())).willReturn(Optional.of(savedBook2));

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
        BookResponse savedBook1 = new BookResponse(
                UUID.randomUUID().toString(),
                "Neues Buch",
                "Beschreibung",
                "Ein Author",
                "isbrn-681894 68181",
                1000,
                new ArrayList<>()
        );
        //Definition des Verhaltens des Mocks/der Attrappe
        given(bookRepository.findById(savedBook1.getId())).willReturn(Optional.of(savedBook1));

        BookResponse savedBook2 = new BookResponse(
                UUID.randomUUID().toString(),
                "Neues Buch",
                "Beschreibung",
                "Ein Author",
                "isbrn-681894 68181",
                2000,
                new ArrayList<>()
        );
        //Definition des Verhaltens des Mocks/der Attrappe
        given(bookRepository.findById(savedBook2.getId())).willReturn(Optional.of(savedBook2));

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

}
