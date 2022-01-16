package com.blz.workshop;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class HotelReservation {

    private List<Hotel> hotels;

    public HotelReservation() {
        this.hotels = new ArrayList<Hotel>();
    }

    public void add(Hotel hotel) {
        hotels.add(hotel);
    }

    public List<Hotel> getHotelList() {
        return this.hotels;
    }

    public Map<Hotel, Integer> searchFor(String date1, String date2) {
        int totalDays = countTotalDays(date1, date2);
        int weekDays = countWeekDays(date1, date2);
        int weekendDays = totalDays - weekDays;
        return getCheapestHotels(weekDays, weekendDays);
    }

    public Map<Hotel, Integer> getCheapestHotels(int weekDays, int weekendDays) {
        Map<Hotel, Integer> hotelCosts = new HashMap<>();
        Map<Hotel, Integer> sortedHotelCosts = new HashMap<>();
        if (hotels.size() == 0)
            return null;
        hotels.stream().forEach(
                n -> hotelCosts.put(n, n.getRegularWeekdayRate() * weekDays + n.getRegularWeekendRate() * weekendDays));
        Integer cheap = hotelCosts.values().stream().min(Integer::compare).get();
        hotelCosts.forEach((k, v) -> {
            if (v.equals(cheap))
                sortedHotelCosts.put(k, v);
        });
        return sortedHotelCosts;
    }

    public int countTotalDays(String date1, String date2) {

        LocalDate startDate = toLocalDate(date1);
        LocalDate endDate = toLocalDate(date2);
        int totalDays = Period.between(startDate, endDate).getDays() + 1;
        return totalDays;
    }

    public int countWeekDays(String date1, String date2) {

        LocalDate startDate = toLocalDate(date1);
        LocalDate endDate = toLocalDate(date2);
        Date startDay = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDay = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDay);

        int weekDays = 0;
        while (!calendar.getTime().after(endDay)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if ((dayOfWeek > 1) && (dayOfWeek < 7)) {
                weekDays++;
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return weekDays;
    }

    public Map<Hotel, Integer> getCheapestAndBestRatedHotels(String date1, String date2) {
        Map<Hotel, Integer> bestHotels = new HashMap<Hotel, Integer>();
        Map<Hotel, Integer> cheapestHotels = searchFor(date1, date2);
        int highestRating = (cheapestHotels.keySet().stream().max(Comparator.comparingInt(Hotel::getRating)).get())
                .getRating();
        cheapestHotels.forEach((k, v) -> {
            if (k.getRating() == highestRating)
                bestHotels.put(k, v);
        });
        return bestHotels;
    }

    public LocalDate toLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}