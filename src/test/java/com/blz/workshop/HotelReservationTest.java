package com.blz.workshop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class HotelReservationTest {

    @Test
    public void whenHotel_AddedToSystem_ShouldGetAdded() {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
        Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 110, 50, 4);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
        Hotel[] hotelList = {hotel1, hotel2, hotel3};
        List<Hotel> hotels = Arrays.asList(hotelList);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        List<Hotel> result = hotelReservation.getHotelList();
        assertEquals(hotels, result);
    }

    @Test
    public void whenGivenDateRange_ShouldReturn_CheapestHotel() {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
        Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 110, 50, 4);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        Map<Hotel, Integer> result = hotelReservation.searchFor("10Sep2020", "11Sep2020");
        result.forEach((k, v) -> System.out.println(k.getName() + " " + v));
        assertNotNull(result);
    }

    @Test
    public void whenGivenHotelAdded_ShouldAddWeekendPrices() {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
        Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 110, 50, 4);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        List<Hotel> hotelList = hotelReservation.getHotelList();
        boolean result = hotelList.get(0).getRegularWeekendRate() == 90
                && hotelList.get(1).getRegularWeekendRate() == 60 && hotelList.get(2).getRegularWeekendRate() == 150;
        assertTrue(result);
    }

    @Test
    public void whenGivenDateRange_ShouldReturn_CheapestHotels() {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
        Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 110, 50, 4);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        Map<Hotel, Integer> result = hotelReservation.searchFor("11Sep2020", "12Sep2020","reward");
        result.forEach((k, v) -> System.out.println(k.getName() + " " + v));
        assertNotNull(result);
    }

    @Test
    public void whenHotelAdded_ToSystemRatingShould_GetAdded() {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
        Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 110, 50, 4);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        List<Hotel> hotelList = hotelReservation.getHotelList();
        boolean result = hotelList.get(0).getRating() == 3 && hotelList.get(1).getRating() == 4
                && hotelList.get(2).getRating() == 5;
        assertTrue(result);
    }

    @Test
    public void whenGivenDateRange_ShouldReturn_CheapestBestRatedHotels() // cheapest best rated hotels.
    {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
        Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 110, 50, 4);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        Map<Hotel, Integer> result = hotelReservation.getCheapestAndBestRatedHotels("11Sep2020", "12Sep2020");
        result.forEach(
                (k, v) -> System.out.println(k.getName() + ", Rating : " + k.getRating() + " and Total Rate " + v));
        assertNotNull(result);
    }
    @Test
    public void whenGivenDateRangeShouldReturnCheapestBestRatedHotels() //best rated hotel.
    {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90, 80, 80, 3);
        Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 110, 50, 4);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        Map<Hotel, Integer> result = hotelReservation.getCheapestAndBestRatedHotels("11Sep2020", "12Sep2020");
        result.forEach(
                (k, v) -> System.out.println(k.getName() + ", Rating : " + k.getRating() + " and Total Rate " + v));
        assertNotNull(result);
    }
    @Test
    public void whenHotelsAddedShouldAddRewardsCustomerRate() //hotel added rewards.
    {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90, 3, 80, 80);
        Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 4, 110, 50);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 5, 100, 40);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        List<Hotel> hotelList = hotelReservation.getHotelList();
        boolean result = hotelList.get(0).getRewardsWeekendRate() == 80 &&
                hotelList.get(0).getRewardsWeekdayRate() == 80 &&
                hotelList.get(1).getRewardsWeekendRate() == 50 &&
                hotelList.get(1).getRewardsWeekdayRate() == 110 &&
                hotelList.get(2).getRewardsWeekendRate() == 40 &&
                hotelList.get(2).getRewardsWeekdayRate() == 100;
        assertTrue(result);
    }
    @Test
    public void whenGivenDateRangeShouldReturnCheapestAndBestRatedHotelForRewardsCustomer() //cheapest best rated hotel for rewards customer.
    {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90, 3, 80, 80);
        Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 4, 110, 50);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 5, 100, 40);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        Map<Hotel, Integer> result;
        try {
            result = hotelReservation.getCheapestAndBestRatedHotels("11Sep2020", "12Sep2020", "reward");
            result.forEach(
                    (k, v) -> System.out.println(k.getName() + ", Rating : " + k.getRating() + " and Total Rate " + v));
            assertNotNull(result);
        } catch (InvalidCustomerException | InvalidDateRangeException e) {
            e.getMessage();
        }
    }
    @Test
    public void whenGivenDateRangeShouldReturnCheapestAndBestRatedHotelForRegularCustomer() //cheapest rated hotel for regular customer.
    {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90, 3, 80, 80);
        Hotel hotel2 = new Hotel("Bridgewood", 150, 50, 4, 110, 50);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 5, 100, 40);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        Map<Hotel, Integer> result;
        try {
            result = hotelReservation.getCheapestAndBestRatedHotels("11Sep2020", "12Sep2020", "regular");
            result.forEach(
                    (k, v) -> System.out.println(k.getName() + ", Rating : " + k.getRating() + " and Total Rate " + v));
            assertNotNull(result);
        } catch (InvalidCustomerException | InvalidDateRangeException e) {
            e.getMessage();
        }
    }
}


