public void playMenu(Pet pet){

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter choice for your pet: 1 - feed , 2 - play , 3 - feelings:: ");
    try{
        int choice = sc.nextInt();
        if (choice < 1 || choice >3){
            throw new IllegalArgumentException();
        }else if (choice == 1){
            pet.feed();
        }else if (choice == 2){
            pet.play();
        }else{
            pet.checkStatus();
        }
    }catch (IllegalArgumentException e){
        System.out.println("Invalid choice. Only 1 or 3.");
    }catch (Exception e){
        System.out.println("Invalid input");
    }

}

void main() {


    Pet myPet = new Pet("Jet", 8,8);
    do {
        playMenu(myPet);
    } while (true);

}
