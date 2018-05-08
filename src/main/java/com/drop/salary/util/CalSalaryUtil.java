package com.drop.salary.util;

import com.drop.salary.pojo.SalaryModel;

import java.math.BigDecimal;

/**
 * @Description: 计算薪酬工具类
 * @Author: Leo
 * @Date: 2018-05-08 下午 4:00
 */
public class CalSalaryUtil {

    private static BigDecimal RATE = new BigDecimal(1.2);

    private static BigDecimal LATER_MONEY = new BigDecimal(2); //每分钟两块

    private static BigDecimal ABSENT_MONEY = new BigDecimal(300);  //旷工每次三百

    private static BigDecimal WORK_MONEY = new BigDecimal(100);    //普通请假100

    private static BigDecimal HOLIDAY_MONEY = new BigDecimal(200);   //节假日请假200
    /**
     * 1.每月按往年平均水平制定当月销售任务（A）
     2.每个员工根据当月销售任务分配个人销售任务（B,B1,B2……）
     3.若员工未完成当月个人销售任务，所有提成减半发放，且不可领取店面业绩超额奖金。
     4.若完成个人销售任务，所有提成正常发放。
     5.若个人实际销售额（B’）超出个人销售任务的20%，则超出部分（C）提成10%奖励给个人，并且超出部分不计入总业绩进行提成。
     6.店面实际业绩（A’）超出当月销售任务20%，则超出部分（D）每人多提1%。
     7.器材销售不计入以上数据，器材销售额的10%即为个人器材销售提成。
     例：
     设：当月工资为Y,A’>A*120%,且普通员工B’>B*120%
     A=B+B1+B2+B3+B4+……+Bn
     C=B’-B*120%
     D=A’-A*120%
     Y=C*10%+(B’-C)*2%+D*1%+(A’-C)*2% +2000
     Y=0.08B’-0.072B+0.03A’-0.012A+2000
     ①若店面实际业绩A’<=A*120%,B’>B*120%,则Y=0.08B’-0.072B+0.02A’+2000
     ②若店面实际业绩A’<=A*120%,B<=B’<B*120%,则Y=0.02B’+0.02A’+2000
     ③若店面实际业绩A’<=A*120%,B’<B,则Y=0.01B’+0.01A’+2000
     ④若店面实际业绩A’>A*120%,B’>B*120%,则Y=0.08B’-0.072B+0.03A’-0.012A+2000
     ⑤若店面实际业绩A’>A*120%,B<=B’<B*120%,则Y=0.02B’+0.01A’+0.012A+2000
     ⑥若店面实际业绩A’>A*120%,B’<B,则Y=0.01B’+0.012A+2000
     * @param model
     * @return
     */
    public static BigDecimal calSalary(SalaryModel model){
        BigDecimal A1 = model.getRealTotal();   //店面实际业绩
        BigDecimal A = model.getTotalTask();    //销售任务
        BigDecimal B1 = model.getRealSale();    //人实际销售额
        BigDecimal B = model.getSaleTask();     //个人销售任务
        BigDecimal Y = model.getBaseSalary();  //总薪酬
        BigDecimal C = B1.subtract(B.multiply(RATE));    //个人提成
        BigDecimal D = A1.subtract(A.multiply(RATE));     //店面提成
        BigDecimal sub = HOLIDAY_MONEY.multiply(model.getHolidayLeave())
                .add(WORK_MONEY.multiply(model.getWorkLeave()))
                .add(LATER_MONEY.multiply(model.getLateTime()))
                .add(ABSENT_MONEY.multiply(model.getAbsent())); //扣款项

        if(D.compareTo(BigDecimal.ZERO) <= 0){ //店面实际业绩A’<=A*120%
            if(C.compareTo(BigDecimal.ZERO)>0){//B’>B*120% Y=0.08B’-0.072B+0.02A’+2000
                Y = Y.add(B1.multiply(new BigDecimal(0.08)))
                        .subtract(B.multiply(new BigDecimal(0.072)))
                        .add(A1.multiply(new BigDecimal(0.02)));
            }else if(B1.compareTo(B)<0){//B’<B Y=0.01B’+0.01A’+2000
                Y = Y.add(B1.multiply(new BigDecimal(0.01)))
                        .add(A1.multiply(new BigDecimal(0.01)));
            }else{//B<=B’<B*120% Y=0.02B’+0.02A’+2000
                Y = Y.add(B1.multiply(new BigDecimal(0.02)))
                        .add(A1.multiply(new BigDecimal(0.02)));
            }
        }else{ //店面实际业绩A’>A*120%
            if(C.compareTo(BigDecimal.ZERO)>0){//B’>B*120%  Y=0.08B’-0.072B+0.03A’-0.012A+2000
                Y = Y.add(B1.multiply(new BigDecimal(0.08)))
                        .subtract(B.multiply(new BigDecimal(0.072)))
                        .add(A1.multiply(new BigDecimal(0.03)))
                        .subtract(A.multiply(new BigDecimal(0.012)));
            }else if(B1.compareTo(B)<0){//B’<B Y=0.01B’+0.012A+2000
                Y = Y.add(B1.multiply(new BigDecimal(0.01)))
                        .add(A.multiply(new BigDecimal(0.012)));
            }else{//B<=B’<B*120% Y=0.02B’+0.01A’+0.012A+2000
                Y = Y.add(B1.multiply(new BigDecimal(0.02)))
                        .add(A1.multiply(new BigDecimal(0.01)))
                        .add(A.multiply(new BigDecimal(0.012)));
            }
        }
        Y = Y.add(model.getEquipSale().multiply(new BigDecimal(0.1)))
                .subtract(sub);
        return Y.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        SalaryModel model = new SalaryModel();
        model.setTotalTask(new BigDecimal(50000));
        model.setRealTotal(new BigDecimal(70000));
        model.setSaleTask(new BigDecimal(10000));
        model.setRealSale(new BigDecimal(15000));
        model.setBaseSalary(new BigDecimal(2000));
        model.setEquipSale(new BigDecimal(50000));
        System.out.println(CalSalaryUtil.calSalary(model).toString());
    }
}
