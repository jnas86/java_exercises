import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Pet {

    private String name;
    private int hunger;
    private int happiness;
    private int happinessTimeThreshold;


    public Pet() {
        this.startHeartbeat();
    }

    public Pet(String name, int hunger, int happiness) {
        this.name = name;
        this.hunger = hunger;
        this.happiness = happiness;
        this.happinessTimeThreshold = 0;
        this.startHeartbeat();
    }

    public void feed(){
        hunger++;
    }

    public void play(){
        happiness++;
    }

    public void checkStatus(){
        System.out.println("Pet: "+name+"\nHunger level: " + hunger + "\nHappiness level: " + happiness);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        if (hunger > 0 && hunger <= 10) {
            this.hunger = hunger;
        } else {
            // Handle invalid input (throw an error or use a default)
            System.out.println("Invalid hunger level! Please pick a value between 5 and 10.");
            // Optional: Default to 5 if invalid
            this.hunger = 5;
        }
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        if (happiness > 0 && happiness <= 10) {
            this.happiness = happiness;
        } else {
            // Handle invalid input (throw an error or use a default)
            System.out.println("Invalid happiness level! Please pick a value between 1 and 10.");
            // Optional: Default to 5 if invalid
            this.happiness = 5;
        }
    }

    public int getHappinessThreshold() {
        return happinessTimeThreshold;
    }

    public void setHappinessThreshold(int happinessThreshold) {
        this.happinessTimeThreshold = happinessThreshold;
    }

    private void changePetName(){
        if (happinessTimeThreshold > 20){
            this.setName("Super "+ name);
            setHunger(10);
            setHappiness(10);
        }
    }

    private void startHeartbeat(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() ->{
            System.out.println("\nhappinessTimeThreshold: " + this.happinessTimeThreshold);
            if(this.happiness >=5){
                this.happinessTimeThreshold += 5;
            }else{
                this.happinessTimeThreshold =0;
            }
            changePetName();
            this.hunger--;
            this.happiness--;
            System.out.println("[SYSTEM]:" + this.name + " just got a a bit more hungry" + "("+this.hunger+")");
            if(this.hunger <= 0){
                System.out.println("Pity..." + this.name + " just left us... :-( ");
                System.exit(0);
            }
            if(this.happiness < 0){
                this.setHappiness(0);
            }
        },5,5, TimeUnit.SECONDS);
    }
}
