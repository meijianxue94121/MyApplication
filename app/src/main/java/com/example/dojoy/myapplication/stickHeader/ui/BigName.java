package com.example.dojoy.myapplication.stickHeader.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dojoy on 2016/10/11.
 * //
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Builder
public class BigName {
    public String first;
    public String last;

    //    public BigName() {
    //    }
    //    public BigName(String first, String last) {
    //        this.first = first;
    //        this.last = last;
    //    }
    //
    //    public String getFirst() {
    //        return first;
    //    }
    //
    //    public void setFirst(String first) {
    //        this.first = first;
    //    }
    //
    //    public String getLast() {
    //        return last;
    //    }
    //
    //    public void setLast(String last) {
    //        this.last = last;
    //    }
}
