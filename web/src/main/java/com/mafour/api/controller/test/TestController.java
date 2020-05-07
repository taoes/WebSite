package com.mafour.api.controller.test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

  @GetMapping("/decimal")
  public List<DecimalTest> test() {

    DecimalTest decimalTest1 = new DecimalTest(5L);
    DecimalTest decimalTest2 = new DecimalTest(34L);
    DecimalTest decimalTest3 = new DecimalTest(5234L);
    DecimalTest decimalTest4 = new DecimalTest(15234L);
    DecimalTest decimalTest5 = new DecimalTest(225234L);
    DecimalTest decimalTest6 = new DecimalTest(null);

    return Arrays.asList(
        decimalTest1, decimalTest2, decimalTest3, decimalTest4, decimalTest5, decimalTest6);
  }

  @Data
  static class DecimalTest {

    private Long cents;

    public DecimalTest(Long cents) {
      this.cents = cents;
    }

    public BigDecimal getYuan() {
      if (this.cents == null) {
        return null;
      }
      return new BigDecimal(this.cents * 1.0F / 100).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String getFormatYuan() {
      if (this.cents == null) {
        return null;
      }
      long money = this.cents;
      NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
      return nf.format(getYuan());
    }
  }
}
