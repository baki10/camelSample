package com.bakigoal.routes.transform.bindy;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.math.BigDecimal;

/**
 * Created by ibakirov on 26.10.16.
 */
@CsvRecord(separator = ",", crlf = "UNIX")
public class PurchaseOrder {
  @DataField(pos = 1)
  private String name;
  @DataField(pos = 2, precision = 2)
  private BigDecimal price;
  @DataField(pos = 3)
  private double amount;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
