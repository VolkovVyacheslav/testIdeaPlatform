package com.example.testideaplatform.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

   private String origin;
   @JsonProperty("origin_name")
   private String originName;
   private String destination;
   @JsonProperty("destination_name")
   private String destinationName;
   @JsonProperty("departure_date")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
   private LocalDate departureDate;
   @JsonProperty("departure_time")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
   private LocalTime departureTime;
   @JsonProperty("arrival_date")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
   private LocalDate arrivalDate;
   @JsonProperty("arrival_time")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
   private LocalTime arrivalTime;
   private String carrier;
   private Integer stops;
   private BigDecimal price;

   public String getOrigin() {
      return origin;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }

   public String getOriginName() {
      return originName;
   }

   public void setOriginName(String originName) {
      this.originName = originName;
   }

   public String getDestination() {
      return destination;
   }

   public void setDestination(String destination) {
      this.destination = destination;
   }

   public String getDestinationName() {
      return destinationName;
   }

   public void setDestinationName(String destinationName) {
      this.destinationName = destinationName;
   }

   public LocalDate getDepartureDate() {
      return departureDate;
   }

   public void setDepartureDate(LocalDate departureDate) {
      this.departureDate = departureDate;
   }

   public LocalTime getDepartureTime() {
      return departureTime;
   }

   public void setDepartureTime(LocalTime departureTime) {
      this.departureTime = departureTime;
   }

   public LocalDate getArrivalDate() {
      return arrivalDate;
   }

   public void setArrivalDate(LocalDate arrivalDate) {
      this.arrivalDate = arrivalDate;
   }

   public LocalTime getArrivalTime() {
      return arrivalTime;
   }

   public void setArrivalTime(LocalTime arrivalTime) {
      this.arrivalTime = arrivalTime;
   }

   public String getCarrier() {
      return carrier;
   }

   public void setCarrier(String carrier) {
      this.carrier = carrier;
   }

   public Integer getStops() {
      return stops;
   }

   public void setStops(Integer stops) {
      this.stops = stops;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   @Override
   public String toString() {
      return "Ticket{" + "\n" +
              "origin='" + origin + "\'\n" +
              ", originName='" + originName + "\'\n" +
              ", destination='" + destination + "\'\n" +
              ", destinationName='" + destinationName + "\'\n" +
              ", departureDate=" + departureDate + "\n" +
              ", departureTime=" + departureTime + "\n" +
              ", arrivalDate=" + arrivalDate + "\n" +
              ", arrivalTime=" + arrivalTime + "\n" +
              ", carrier='" + carrier + "\'\n" +
              ", stops=" + stops + "\n" +
              ", price=" + price + "\n" +
              '}' + "\n";
   }
}
