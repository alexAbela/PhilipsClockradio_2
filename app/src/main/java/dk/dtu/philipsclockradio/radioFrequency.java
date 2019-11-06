package dk.dtu.philipsclockradio;

public class radioFrequency {
    private String modulation;
    private double frequency;


    public radioFrequency(){
        modulation = "F";
        frequency =  88;

    }

    public void changeModulation(){
        if(this.modulation.equals("F")){
            setModulation("A");
            //setFrequency(54);
        } else {
            setModulation("F");
           // setFrequency(88);
        }
    }




    private void setModulation(String modulation) {
        this.modulation = modulation;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getFrequency() {
        return frequency;
    }

    public String getModulation() {
        return modulation;
    }
}

