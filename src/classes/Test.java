package classes;

import toolsPackage.CardUtil;

import java.util.Scanner;

public class Test
{
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        CardUtil cardUtil = new CardUtil();
        cardUtil.initScene();
        String a;
        int cont = 0;
        one:
        while(true)
        {
            if(cont==3)
            {
                System.out.println("您已经尝试了3次，均登录失败,请系统即将退出，请稍后再试");
                Thread.sleep(1000);
                System.exit(0);
            }
            System.out.println("********欢迎使用嗖嗖移动业务大厅********");
            System.out.println("1.用户登录\t\t2.用户注册\t\t3.使用嗖嗖\t\t4.话费充值\t\t5.资费说明\t\t6.退出系统\n请选择：");
            a = sc.next();
            switch (a)
            {
                case "1":
                    System.out.println("请输入用户名:");
                    String num = sc.next();
                    System.out.println("请输入密码");
                    String pwd = sc.next();
                    if(cardUtil.isExitCard(num,pwd))
                    {
                        System.out.println("登录成功");
                        two:
                        while(true)
                        {
                            System.out.println("********嗖嗖移动用户菜单********");
                            System.out.println("1.本月账单查询\n2.套餐余量查询\n3.打印消费账单\n4.套餐变更\n5.办理退网\n请选择(输入1~5选择功能，其他键返回上一级):");
                            a = sc.next();
                            switch (a)
                            {
                                case "1":
                                    cardUtil.showRemainDetail(num);
                                    break;
                                case "2":
                                    cardUtil.showAmountDetail(num);
                                    break;
                                case "3":
                                    System.out.println("3");
                                    cardUtil.printConSumInfo(num);
                                    break;
                                case "4":
                                    System.out.println("*****套餐变更*****");
                                    System.out.println("1.话痨套餐\t2.网虫套餐\t3.超人套餐,请选择套餐(输入序号):");
                                    String s = sc.next();
                                    cardUtil.changingPack(num,s);
                                    break;
                                case "5":
                                    System.out.println("*****办理退网*****");
                                    cardUtil.delCard(num);
                                    break;
                                default:
                                    System.out.println("返回上一级");
                                    break two;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("输入的用户名或密码错误,也可能是没有该用户");
                        cont++;
                    }
                    break;
                case "2":
                    System.out.println("执行用户注册功能");
                    cardUtil.createNumber();
                    break;
                case "3":
                    System.out.println("执行使用嗖嗖功能");
                    System.out.println("请输入手机号：");
                    String aa = sc.next();
                    cardUtil.addConSumInfo(aa);
                    break;
                case "4":
                    System.out.println("执行话费充值功能");
                    System.out.println("请输入充值卡号：");
                    String nums = sc.next();
                    System.out.println("请输入充值金额：");
                    double mon = sc.nextDouble();
                    cardUtil.chargeMoney(nums,mon);
                    break;
                case "5":
                    System.out.println("执行资费说明功能");
                    cardUtil.showDescription();
                    break;
                case "6":
                    System.out.println("确认要退出吗(y/n)");
                    String exit = sc.next();
                    if ("y".equalsIgnoreCase(exit))
                    {
                        break one;
                    }
                    else
                    {
                        System.out.println("已返回");
                    }
                    break;
                default:
                    System.out.println("输入的不是功能数字，请重试");
                    break;
            }
        }
    }
}
