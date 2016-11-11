/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javax.persistence.*;
/**
 *
 * @author T450
 */
@Entity
public class Ticket {
    @Id
    private String ticketID;
    private String ticketName;
    private double ticketPrice;
    private int totalQty;
    private int qtySold;
    
    public Ticket(){
        
    }

    public Ticket(String ticketID, String ticketName, double ticketPrice, int totalQty, int qtySold) {
        this.ticketID = ticketID;
        this.ticketName = ticketName;
        this.ticketPrice = ticketPrice;
        this.totalQty = totalQty;
        this.qtySold = qtySold;
    }
    
    

    public String getTicketID() {
        return ticketID;
    }

    public String getTicketName() {
        return ticketName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public int getQtySold() {
        return qtySold;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public void setQtySold(int qtySold) {
        this.qtySold = qtySold;
    }
    
    
    
}
