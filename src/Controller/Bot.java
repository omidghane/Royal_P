package Model;

public class Bot extends Player{
    public Bot(){
        Task();
    }

    public void Task(){
        initialize_cardBar();
    }

    private void initialize_cardBar() {
        for(int i = 0 ; i < 4 ;i++){
            getCard_bar().add(give_bar_card());
            System.out.println(getCard_bar().get(i).toString());
        }
    }
}
