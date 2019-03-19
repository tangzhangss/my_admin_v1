package zyrs.xyz.obadmin.bean;

/**
 * Created by Administrator on 2019/3/12.
 * 医生余额详情
 */
public class VdoctorBalance {
    private int sum;//总金额
    private int used;//已结算的
    private int available;//可结算的
    private int freeze;//冻结的

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    @Override
    public String toString() {
        return "VdoctorBalance{" +
                "sum=" + sum +
                ", used=" + used +
                ", available=" + available +
                ", freeze=" + freeze +
                '}';
    }
}
