package classes;

public class MobileCard
{
    public String cardNumber;
    public String userName;
    public String passWord;
    public ServicePackage serPackage;
    public double conSumAmount;
    public double money;
    public int realTalkTime;
    public int realSMSCount;
    public int realFlow;

    public MobileCard(String cardNumber, String userName, String passWord, ServicePackage serPackage, double conSumAmount, double money, int realTalkTime, int realSMSCount, int realFlow) {
        this.cardNumber = cardNumber;
        this.userName = userName;
        this.passWord = passWord;
        this.serPackage = serPackage;
        this.conSumAmount = conSumAmount;
        this.money = money;
        this.realTalkTime = realTalkTime;
        this.realSMSCount = realSMSCount;
        this.realFlow = realFlow;
    }
}
