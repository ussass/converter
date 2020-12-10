package ru.trofimov.models;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table(name = "conversion_history")
public class ConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "history_date")
    private int date;

    @Column(name = "original_value")
    private double originalValue;

    @Column(name = "result_value")
    private double resultValue;

    @Column(name = "original_currency")
    private String originalCurrency;

    @Column(name = "result_currency")
    private String resultCurrency;

    @Column(name = "original_char_code")
    private String originalCharCode;

    @Column(name = "result_char_code")
    private String resultCharCode;

    public ConversionHistory() {
    }

    public ConversionHistory(int date, double originalValue, double resultValue, String originalCurrency, String resultCurrency, String originalCharCode, String resultCharCode) {
        this.date = date;
        this.originalValue = originalValue;
        this.resultValue = resultValue;
        this.originalCurrency = originalCurrency;
        this.resultCurrency = resultCurrency;
        this.originalCharCode = originalCharCode;
        this.resultCharCode = resultCharCode;
    }

    public String getStringDate() {
        int day = date % 100;
        int month = date % 10000 / 100;
        int year = date / 10000 + 2000;
        return day + "." + month + "." + year;
    }

    public String getMoneyFormat(boolean isOriginal){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return isOriginal? decimalFormat.format(originalValue) : decimalFormat.format(resultValue);
    }

    public String getForHistoryTable(boolean isOriginal){
        return getMoneyFormat(isOriginal) + " " +
                (isOriginal ? originalCurrency : resultCurrency) + " (" +
                (isOriginal ? "1" : String.format("%.4f", resultValue / originalValue)) + " " +
                (isOriginal ? originalCharCode : resultCharCode) + ")";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(double originalValue) {
        this.originalValue = originalValue;
    }

    public double getResultValue() {
        return resultValue;
    }

    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public String getResultCurrency() {
        return resultCurrency;
    }

    public void setResultCurrency(String resultCurrency) {
        this.resultCurrency = resultCurrency;
    }

    public String getOriginalCharCode() {
        return originalCharCode;
    }

    public void setOriginalCharCode(String originalCharCode) {
        this.originalCharCode = originalCharCode;
    }

    public String getResultCharCode() {
        return resultCharCode;
    }

    public void setResultCharCode(String resultCharCode) {
        this.resultCharCode = resultCharCode;
    }

    @Override
    public String toString() {
        return "ConversionHistory{" +
                "id=" + id +
                ", date=" + date +
                ", originalValue=" + originalValue +
                ", resultValue=" + resultValue +
                ", originalCurrency='" + originalCurrency + '\'' +
                ", resultCurrency='" + resultCurrency + '\'' +
                ", originalCharCode='" + originalCharCode + '\'' +
                ", resultCharCode='" + resultCharCode + '\'' +
                '}';
    }
}
