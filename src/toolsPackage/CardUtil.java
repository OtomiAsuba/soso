package toolsPackage;

import classes.*;
import java.io.*;
import java.util.*;

public class CardUtil
{
    //初始化2个ArrayList集合
    //该集合用于存放用户
    public static ArrayList <MobileCard> cards = new ArrayList<> ();
    //该集合用于存放消费信息
    public static ArrayList <MSG> msgInfo = new ArrayList<>();
    //定义一个初始化方法，将信息初始化
    public void initScene()
    {
        //创建对象，是得之后可以将对象加入集合
        MobileCard A=new MobileCard("13913900001", "A", "123", new TalkPackage(),58, 100, 0, 0, 0);
        MobileCard B=new MobileCard("13913900002", "B", "456", new NetPackage(),68, 100, 0, 0, 0);
        MobileCard C=new MobileCard("13913900003", "C", "789", new SuperPackage(),78, 200, 0, 0, 0);
        //将卡对象添加到集合中
        CardUtil.cards.add(A);
        CardUtil.cards.add(B);
        CardUtil.cards.add(C);

        //初始化一条信息，使得信息集合能够正常遍历
        MSG D = new MSG("99999999999","此信息仅为了正常遍历而存，不能输出","非任何类型");
                                    /*
                                    "13913900001"
                                    */
        CardUtil.msgInfo.add(D);
    }
    //登录功能
    public boolean isExitCard(String number,String passWord)
    {
        for (MobileCard card : cards)
        {
            if ((number.equals(card.cardNumber) && (passWord.equals(card.passWord))))
            {
                return true;
            }
        }
        return false;
    }
    //重写登录方法，方便后续的调用
    public boolean isExitCard(String number)
    {
        for (MobileCard card : cards)
        {
            if (number.equals(card.cardNumber))
            {
                return true;
            }
        }
        return false;
    }

    //注册功能
    public void createNumber()
    {
        Scanner sc = new Scanner(System.in);
        //实例化随机数对象
        Random r = new Random();
        //实例化一个数组，用于存放随机生成的手机号
        String [] arr = new String[9];
        //用于接受输入的手机号序号
        int nums;
        //用户接受套餐序号
        String str2;
        System.out.println("请选择卡号");
        //循环9次(要生成9个手机号)
       for (int i = 0; i < 9; i++)
        {
            //每次循环都创建一个num变量，用于和手机号拼接
            String num = "139";
            //二层循环，循环8次（139后要有8位数字）
            for (int j = 0; j < 8; j++)
            {
                //字符串拼接到139之后
                num += r.nextInt(9);
                //将num赋值给数组
            }
            arr[i] = num;
            //遍历集合，判断生成的随机数卡号是否已经存在，如果存在，就使得本次循环无效（i--）
            for (MobileCard card : cards)
            {
                if (card.cardNumber.equals(arr[i]))
                {
                    i--;
                    break;
                }
            }
        }
        //上面已经生成完成，接下来遍历数组，将手机号打印出来
        for (int i = 0; i < 9; i++)
        {
            System.out.print((i+1)+"."+arr[i]+" ");
            //判断当前循环次数，若是3的倍数，则换行
            if((i+1)%3==0)
            {
                System.out.println();
            }
        }


        /*该方法不能生成1390的手机号，且可能生成较多的随机数，故弃用，有兴趣可以看看
        for (int i = 0; i < 9; i++)
        {
            String num = "139";
            one:
            while(true)
            {
                int n= r.nextInt(100000000);
                for (int j = 0; j < cards.size(); j++)
                {
                    if((!(cards.get(j).cardNumber.equals(num))&&(n>9999999)))
                    {
                        num += n;
                        arr[i] = num;
                        con++;
                        break one;
                    }
                }
            }
            System.out.print((i+1)+"."+arr[i]+" ");
            if(con%3==0)
            {
                System.out.println();
            }
        }
        */

        System.out.println("请选择卡号(输入1~9的序号)");
        nums = sc.nextInt();
        System.out.println("1.话痨套餐\t2.网虫套餐\t3.超人套餐,请选择套餐(输入序号):");
        str2 = sc.next();
        System.out.println("请输入姓名");
        String names = sc.next();
        System.out.println("请输入密码");
        String pwd = sc.next();

        System.out.println("请输入预存的话费金额");
        double sal = sc.nextDouble();
        //判断输入的金额是否能够支付的了对应选择的套餐，不足则给出提示并重新输入
        while ((sal<58&&"1".equals(str2))||(sal<68&&"2".equals(str2))||(sal<78&&"3".equals(str2)))
        {
            System.out.println("您预存的话费金额不足以支付本月固定套餐餐费,请重新充值");
            sal = sc.nextDouble();
        }
        //根据选择的套餐执行对应的操作
        switch (str2)
        {
            case "1":
                //实例化一个卡对象用于赋值（将金额对应的减去），下同
                MobileCard newCard = new MobileCard(arr[nums-1],names,pwd,new TalkPackage(),58,(sal-58),0,0,0);
                cards.add(newCard);
                System.out.println("注册成功!卡号："+arr[nums-1]+" 用户名"+names+" 当前余额"+(sal-58)+"元");
                System.out.println("话唠套餐:通话时长为200分钟/月，短信条数为50条/月，上网流量为0GB/月。");
                break;
            case "2":
                MobileCard newCard2 = new MobileCard(arr[nums-1],names,pwd,new NetPackage(),68,(sal-68),0,0,0);
                cards.add(newCard2);
                System.out.println("注册成功!卡号："+arr[nums-1]+" 用户名"+names+" 当前余额"+(sal-68)+"元");
                System.out.println("网虫套餐:通话时长为0分钟/月，短信条数为0条/月，上网流量为5GB/月。");
                break;
            case "3":
                MobileCard newCard3 = new MobileCard(arr[nums-1],names,pwd,new SuperPackage(),78,(sal-78),0,0,0);
                cards.add(newCard3);
                System.out.println("注册成功!卡号："+arr[nums-1]+" 用户名"+names+" 当前余额"+(sal-78)+"元");
                System.out.println("超人套餐:通话时长为200分钟/月，短信条数为100条/月，上网流量为1GB/月。");
        }
    }

    //删号功能
    public void delCard(String number)
    {
        //循环遍历集合，找到对应的卡号
        for (int i = 0; i < cards.size(); i++)
        {
            if (number.equals(cards.get(i).cardNumber))
            {
                //执行删号功能并退出系统
                System.out.println("卡号"+cards.get(i).cardNumber+"办理退网成功！");
                cards.remove(i);
                System.out.println("谢谢使用");
                System.exit(0);
                break;
            }
        }
    }

    //账单查询功能
    public void showRemainDetail(String number)
    {
        Scanner sc = new Scanner(System.in);
        //遍历集合找到对应的卡号
        for (MobileCard card : cards)
        {
            if (number.equals(card.cardNumber))
            {
                //根据卡号的套餐类型查询账单
                System.out.println("*****本月账单查询*****");
                System.out.println("您的卡号:" + card.cardNumber + ",当月账单：");
                if (card.serPackage instanceof TalkPackage)
                {
                    System.out.println("套餐资费58.0元");
                    System.out.println("合计" + card.conSumAmount);
                    System.out.println("账户余额:" + card.money + "元");
                }
                else if (card.serPackage instanceof NetPackage)
                {
                    System.out.println("套餐资费68.0元");
                    System.out.println("合计" + card.conSumAmount);
                    System.out.println("账户余额:" + card.money + "元");
                }
                else
                {
                    System.out.println("套餐资费78.0元");
                    System.out.println("合计" + card.conSumAmount);
                    System.out.println("账户余额:" + card.money + "元");
                }
            }
        }
        //按0下一步
        System.out.println("按0进行下一步");
        if(sc.next().equals("0"))
        {
            System.out.println("查看完成,返回上一级");
        }
        else
        {
            System.out.println("查看完成,返回上一级");
        }
    }

    //套餐余量功能
    public void showAmountDetail(String number)
    {
        Scanner sc = new Scanner(System.in);
        for (MobileCard card : cards)
        {
            if (number.equals(card.cardNumber))
            {
                System.out.println("*****套餐余量查询*****");
                System.out.println("您的卡号:" + card.cardNumber + ",套餐内剩余:");
                if (card.serPackage instanceof TalkPackage)
                {
                    System.out.println("您是话痨套餐");
                    //根据套餐将超过的部分变为0，下同
                    double a = 200 - (card.realTalkTime);
                    if (a <= 0)
                    {
                        a = 0;
                    }

                    int b = 50 - (card.realSMSCount);
                    if (b <= 0)
                    {
                        b = 0;
                    }
                    System.out.println("通话时长：" + a + "分钟");
                    System.out.println("短信条数：" + b + "条");
                }
                else if (card.serPackage instanceof NetPackage)
                {
                    System.out.println("您是网虫套餐");
                    double c = 5 - (card.realFlow);
                    if (c <= 0)
                    {
                        c = 0;
                    }
                    System.out.println("上网流量：" + c + "GB");
                }
                else
                {
                    System.out.println("您是超人套餐");
                    double a = 200 - (card.realTalkTime);
                    if (a <= 0)
                    {
                        a = 0;
                    }

                    int b = 100 - (card.realSMSCount);
                    if (b <= 0) {
                        b = 0;
                    }

                    double c = 1 - (card.realFlow);
                    if (c <= 0)
                    {
                        c = 0;
                    }
                    System.out.println("通话时长：" + a + "分钟");
                    System.out.println("短信条数：" + b + "条");
                    System.out.println("上网流量：" + c + "GB");
                }
            }
        }
        System.out.println("按0进行下一步");
        if(sc.next().equals("0"))
        {
            System.out.println("查看完成,返回上一级");
        }
        else
        {
            System.out.println("查看完成,返回上一级");
        }
    }

    //使用嗖嗖功能
    public void addConSumInfo(String number)
    {
        //调用方法，判断卡号是否存在
        if(this.isExitCard(number))
        {
            //实例化随机数对象
            Random r = new Random();
            //创建一个数组，用于存放场景
            String [] scenes = new String[]{"通话90分钟","通话30分钟","发送5条短信",
                                            "发送50条短信","使用流量1GB","使用流量2GB"};

                //生成0~5的随机数,赋值给一个变量
                int rdNum = r.nextInt(6);
                //使用switch调用变量，随机执行场景
                one://该标签是为了让套餐不符合条件的时候退出,然后重新生成随机数
                switch (rdNum)
                {
                    //当随机数为0时，数组下标为0，执行通话90分钟，该场景当套餐为网虫套餐时，不能使用
                    case 0:
                        System.out.println(scenes[rdNum]);
                        //遍历集合，找到number参数对应的集合元素
                        for (int i = 0; i < cards.size(); i++)
                        {
                            if(cards.get(i).cardNumber.equals(number))
                            {
                                //此处，要先判断该卡的套餐，如果是网虫套餐，就要退出，然后重新生成随机数
                                if(!(cards.get(i).serPackage instanceof NetPackage))
                                {
                                    //找到该卡需要变更的信息，创建一个变量接受并且改变
                                    int change = 90;
                                    change += cards.get(i).realTalkTime;
                                    //计算套餐余量
                                    double less = 200-change;
                                    //创建一个改变金额的变量
                                    double chMoney = cards.get(i).money;
                                    //创建一个改变金额的变量
                                    double chSum = cards.get(i).conSumAmount;
                                    //当套餐余量不足时
                                    if (less<0)
                                    {
                                        //取得不足的部分，由于是负数，需要转化为绝对值，之后再计算消费的金额
                                        chMoney = cards.get(i).money - (Math.abs(less)*0.2);
                                        chSum += (Math.abs(less)*0.2);
                                        //当余额不足时，给出提示
                                        if(chMoney<0)
                                        {
                                            System.out.println("余额不足，请先充值");
                                        }
                                    }
                                    //找到该卡的所有信息，实例化一个变更后的卡对象，并执行变更
                                    MobileCard cardMsgChange = new MobileCard(cards.get(i).cardNumber,cards.get(i).userName,cards.get(i).passWord,cards.get(i).serPackage,chSum,chMoney,change,cards.get(i).realSMSCount,cards.get(i).realFlow);
                                    cards.set(i,cardMsgChange);
                                    //执行增加账单操作,实例化一个信息类的对象
                                    MSG msg = new MSG(number,scenes[rdNum],"通话");
                                    //判断信息集合中，是否存有过该卡号，有则添加，没有就创建
                                    for (int j = 0; j <msgInfo.size() ; j++)
                                    {
                                        //System.out.println(msgInfo.get(j).CardNumber+"     "+msgInfo.get(j).info);
                                        if(msgInfo.get(j).CardNumber.equals(number))
                                        {
                                            System.out.println("已有记录，添加");
                                            msgInfo.add(msg);
                                            break one;
                                        }
                                    }
                                    System.out.println("没有记录，创建");
                                    msgInfo.add(msg);
                                }
                                else
                                {
                                    System.out.println("当前套餐不支持此类消费：重新执行此操作");
                                }
                                break one;
                            }
                        }
                        break;





                    case 1:
                        System.out.println(scenes[rdNum]);
                        for (int i = 0; i < cards.size(); i++)
                        {
                            if(cards.get(i).cardNumber.equals(number))
                            {
                                if(!(cards.get(i).serPackage instanceof NetPackage))
                                {
                                    int change = 30;
                                    change += cards.get(i).realTalkTime;
                                    double less = 200-change;
                                    double chMoney = cards.get(i).money;
                                    double chSum = cards.get(i).conSumAmount;
                                    if (less<0)
                                    {
                                        chMoney = cards.get(i).money - (Math.abs(less)*0.2);
                                        chSum += (Math.abs(less)*0.2);
                                        if(chMoney<0)
                                        {
                                            System.out.println("余额不足，请先充值");
                                        }
                                    }
                                    MobileCard cardMsgChange = new MobileCard(cards.get(i).cardNumber,cards.get(i).userName,cards.get(i).passWord,cards.get(i).serPackage,chSum,chMoney,change,cards.get(i).realSMSCount,cards.get(i).realFlow);
                                    cards.set(i,cardMsgChange);
                                    MSG msg = new MSG(number,scenes[rdNum],"通话");
                                    for (int j = 0; j <msgInfo.size() ; j++)
                                    {
                                        if(msgInfo.get(j).CardNumber.equals(number))
                                        {
                                            System.out.println("已有记录，添加");
                                            msgInfo.add(msg);
                                            break one;
                                        }
                                    }
                                    System.out.println("没有记录，创建");
                                    msgInfo.add(msg);
                                }
                                else
                                {
                                    System.out.println("当前套餐不支持此类消费：重新执行此操作");
                                }
                                break one;
                            }
                        }
                        break;






                    case 2:
                        System.out.println(scenes[rdNum]);
                        for (int i = 0; i < cards.size(); i++)
                        {
                            if(cards.get(i).cardNumber.equals(number))
                            {
                                if(!(cards.get(i).serPackage instanceof NetPackage))
                                {
                                    double less;
                                    int change = 5;
                                    change += cards.get(i).realSMSCount;
                                    if(cards.get(i).serPackage instanceof TalkPackage)
                                    {
                                        less = 50-change;
                                    }
                                    else
                                    {
                                        less = 100-change;
                                    }
                                    double chMoney = cards.get(i).money;
                                    double chSum = cards.get(i).conSumAmount;
                                    if (less<0)
                                    {
                                        chMoney = cards.get(i).money - (Math.abs(less)*0.1);
                                        chSum += (Math.abs(less)*0.1);
                                        if(chMoney<0)
                                        {
                                            System.out.println("余额不足，请先充值");
                                        }
                                    }
                                    MobileCard cardMsgChange = new MobileCard(cards.get(i).cardNumber,cards.get(i).userName,cards.get(i).passWord,cards.get(i).serPackage,chSum,chMoney,cards.get(i).realTalkTime,change,cards.get(i).realFlow);
                                    cards.set(i,cardMsgChange);
                                    MSG msg = new MSG(number,scenes[rdNum],"短信");
                                    for (int j = 0; j <msgInfo.size() ; j++)
                                    {
                                        if(msgInfo.get(j).CardNumber.equals(number))
                                        {
                                            System.out.println("已有记录，添加");
                                            msgInfo.add(msg);
                                            break one;
                                        }
                                    }
                                    System.out.println("没有记录，创建");
                                    msgInfo.add(msg);
                                }
                                else
                                {
                                    System.out.println("当前套餐不支持此类消费：重新执行此操作");
                                }
                                break one;
                            }
                        }
                        break;





                    case 3:
                        System.out.println(scenes[rdNum]);
                        for (int i = 0; i < cards.size(); i++)
                        {
                            if(cards.get(i).cardNumber.equals(number))
                            {
                                if(!(cards.get(i).serPackage instanceof NetPackage))
                                {
                                    double less;
                                    int change = 50;
                                    change += cards.get(i).realSMSCount;
                                    if(cards.get(i).serPackage instanceof TalkPackage)
                                    {
                                        less = 50-change;
                                    }
                                    else
                                    {
                                        less = 100-change;
                                    }
                                    double chMoney = cards.get(i).money;
                                    double chSum = cards.get(i).conSumAmount;
                                    if (less<0)
                                    {
                                        chMoney = cards.get(i).money - (Math.abs(less)*0.1);
                                        chSum += (Math.abs(less)*0.1);
                                        if(chMoney<0)
                                        {
                                            System.out.println("余额不足，请先充值");
                                        }
                                    }
                                    MobileCard cardMsgChange = new MobileCard(cards.get(i).cardNumber,cards.get(i).userName,cards.get(i).passWord,cards.get(i).serPackage,chSum,chMoney,cards.get(i).realTalkTime,change,cards.get(i).realFlow);
                                    cards.set(i,cardMsgChange);
                                    MSG msg = new MSG(number,scenes[rdNum],"短信");
                                    for (int j = 0; j <msgInfo.size() ; j++)
                                    {
                                        if(msgInfo.get(j).CardNumber.equals(number))
                                        {
                                            System.out.println("已有记录，添加");
                                            msgInfo.add(msg);
                                            break one;
                                        }
                                    }
                                    System.out.println("没有记录，创建");
                                    msgInfo.add(msg);
                                }
                                else
                                {
                                    System.out.println("当前套餐不支持此类消费：重新执行此操作");
                                }
                                break one;
                            }
                        }
                        break;





                    case 4:
                        System.out.println(scenes[rdNum]);
                        for (int i = 0; i < cards.size(); i++)
                        {
                            if(cards.get(i).cardNumber.equals(number))
                            {
                                if(!(cards.get(i).serPackage instanceof TalkPackage))
                                {
                                    double less;
                                    int change = 1;
                                    change += cards.get(i).realFlow;
                                    if(cards.get(i).serPackage instanceof NetPackage)
                                    {
                                        less = 5-change;
                                    }
                                    else
                                    {
                                        less = 1-change;
                                    }
                                    double chMoney = cards.get(i).money;
                                    double chSum = cards.get(i).conSumAmount;
                                    if (less<0)
                                    {
                                        chMoney = cards.get(i).money - (Math.abs(less)*10);
                                        chSum += (Math.abs(less)*10);
                                        if(chMoney<0)
                                        {
                                            System.out.println("余额不足，请先充值");
                                        }
                                    }
                                    MobileCard cardMsgChange = new MobileCard(cards.get(i).cardNumber,cards.get(i).userName,cards.get(i).passWord,cards.get(i).serPackage,chSum,chMoney,cards.get(i).realTalkTime,cards.get(i).realSMSCount,change);
                                    cards.set(i,cardMsgChange);
                                    MSG msg = new MSG(number,scenes[rdNum],"流量");
                                    for (int j = 0; j <msgInfo.size() ; j++)
                                    {
                                        if(msgInfo.get(j).CardNumber.equals(number))
                                        {
                                            System.out.println("已有记录，添加");
                                            msgInfo.add(msg);
                                            break one;
                                        }
                                    }
                                    System.out.println("没有记录，创建");
                                    msgInfo.add(msg);
                                }
                                else
                                {
                                    System.out.println("当前套餐不支持此类消费：重新执行此操作");
                                }
                                break one;
                            }
                        }
                        break;






                    case 5:
                        System.out.println(scenes[rdNum]);
                        for (int i = 0; i < cards.size(); i++)
                        {
                            if(cards.get(i).cardNumber.equals(number))
                            {
                                if(!(cards.get(i).serPackage instanceof TalkPackage))
                                {
                                    double less;
                                    int change = 2;
                                    change += cards.get(i).realFlow;
                                    if(cards.get(i).serPackage instanceof NetPackage)
                                    {
                                        less = 5-change;
                                    }
                                    else
                                    {
                                        less = 1-change;
                                    }
                                    double chMoney = cards.get(i).money;
                                    double chSum = cards.get(i).conSumAmount;
                                    if (less<0)
                                    {
                                        chMoney = cards.get(i).money - (Math.abs(less)*10);
                                        chSum += (Math.abs(less)*10);
                                        if(chMoney<0)
                                        {
                                            System.out.println("余额不足，请先充值");
                                        }
                                    }
                                    MobileCard cardMsgChange = new MobileCard(cards.get(i).cardNumber,cards.get(i).userName,cards.get(i).passWord,cards.get(i).serPackage,chSum,chMoney,cards.get(i).realTalkTime,cards.get(i).realSMSCount,change);
                                    cards.set(i,cardMsgChange);
                                    MSG msg = new MSG(number,scenes[rdNum],"流量");
                                    for (int j = 0; j <msgInfo.size() ; j++)
                                    {
                                        if(msgInfo.get(j).CardNumber.equals(number))
                                        {
                                            System.out.println("已有记录，添加");
                                            msgInfo.add(msg);
                                            break one;
                                        }
                                    }
                                    System.out.println("没有记录，创建");
                                    msgInfo.add(msg);
                                }
                                else
                                {
                                    System.out.println("当前套餐不支持此类消费：重新执行此操作");
                                }
                                break one;
                            }
                        }
                        break;



                }
            }
        else
        {
            System.out.println("您输入的卡号不存在");
        }
    }


    //资费说明功能
    public void showDescription() throws Exception
    {
        Scanner sc = new Scanner(System.in);
        FileInputStream fi = new FileInputStream("cd ../../read/infomsg.txt");
        InputStreamReader reader = new InputStreamReader(fi);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp;
        while((strTmp = buffReader.readLine())!=null)
        {
            System.out.println(strTmp);
        }
        buffReader.close();

        System.out.println("按0进行下一步");
        if(sc.next().equals("0"))
        {
            System.out.println("查看完成,返回上一级");
        }
        else
        {
            System.out.println("查看完成,返回上一级");
        }
    }

    //改变套餐功能
    public void changingPack(String number,String packNum)
    {
        for (int i = 0; i < cards.size(); i++)
        {
            if(cards.get(i).cardNumber.equals(number))
            {
                switch (packNum)
                {
                    case "1":
                        if(cards.get(i).serPackage instanceof TalkPackage)
                        {
                            System.out.println("对不起，您已是该套餐用户，无需换套餐！");
                        }
                        else if(cards.get(i).money<58)
                        {
                            System.out.println("对不起，您的余额不足以支付新套餐的本月资费，请充值后再办理更换套餐业务！");
                        }
                        else
                        {
                            double newMoney = cards.get(i).money-58;
                            MobileCard cardChangeSer1 = new MobileCard(cards.get(i).cardNumber,cards.get(i).userName,cards.get(i).passWord,new TalkPackage(),cards.get(i).conSumAmount,newMoney,0,0,0);
                            cards.set(i,cardChangeSer1);
                            System.out.println("办理成功,您当前的套餐是————话痨套餐");
                        }
                        break;

                    case "2":
                        if(cards.get(i).serPackage instanceof NetPackage)
                        {
                            System.out.println("对不起，您已是该套餐用户，无需换套餐！");
                        }
                        else if(cards.get(i).money<68)
                        {
                            System.out.println("对不起，您的余额不足以支付新套餐的本月资费，请充值后再办理更换套餐业务！");
                        }
                        else
                        {
                            double newMoney = cards.get(i).money-68;
                            MobileCard cardChangeSer2 = new MobileCard(cards.get(i).cardNumber,cards.get(i).userName,cards.get(i).passWord,new NetPackage(),cards.get(i).conSumAmount,newMoney,0,0,0);
                            cards.set(i,cardChangeSer2);
                            System.out.println("办理成功,您当前的套餐是————网虫套餐");
                        }
                        break;

                    case "3":
                        if(cards.get(i).serPackage instanceof SuperPackage)
                        {
                            System.out.println("对不起，您已是该套餐用户，无需换套餐！");
                        }
                        else if(cards.get(i).money<78)
                        {
                            System.out.println("对不起，您的余额不足以支付新套餐的本月资费，请充值后再办理更换套餐业务！");
                        }
                        else
                        {
                            double newMoney = cards.get(i).money-78;
                            MobileCard cardChangeSer3 = new MobileCard(cards.get(i).cardNumber,cards.get(i).userName,cards.get(i).passWord,new SuperPackage(),cards.get(i).conSumAmount,newMoney,0,0,0);
                            cards.set(i,cardChangeSer3);
                            System.out.println("办理成功,您当前的套餐是————话痨套餐");
                        }
                        break;


                }
            }
        }
    }

    //打印功能
    public void printConSumInfo(String number) throws Exception {

        //需要存放的路径（要先创建文件）
        File f=new File("E:\\devlop\\java_exp\\soso_cop\\txt_src\\p.txt");


        FileOutputStream fos1=new FileOutputStream(f);
        OutputStreamWriter dos1=new OutputStreamWriter(fos1);

        dos1.write("***********"+number+"消费记录***********\n");
        dos1.write("序号\t类型\t数据\n");
        int cont = 1;
        for (MSG msg : msgInfo)
        {
            if (msg.CardNumber.equals(number))
            {
                dos1.write(cont + "\t\t" + msg.type + "\t" + msg.info + "\n");
                cont++;
            }
        }
        System.out.println("打印完成，请前往E:\\devlop\\java_exp\\soso_cop\\txt_src\\下查看");
        dos1.close();
    }

    //充值功能
    public void chargeMoney(String number,double money)
    {
        boolean flag = false;
        for (int i = 0; i < cards.size(); i++)
        {
            if (number.equals(cards.get(i).cardNumber))
            {
                flag = true;
                double newMoney = money;
                newMoney += cards.get(i).money;
                MobileCard cardChangeMoney = new MobileCard(cards.get(i).cardNumber,cards.get(i).userName,cards.get(i).passWord,cards.get(i).serPackage,cards.get(i).conSumAmount,newMoney,cards.get(i).realTalkTime,cards.get(i).realSMSCount,cards.get(i).realFlow);
                cards.set(i,cardChangeMoney);
                System.out.println("充值成功，当前话费为"+cards.get(i).money+"元");
            }
        }
        if(!flag)
        {
            System.out.println("该卡不存在");
        }
    }
}
