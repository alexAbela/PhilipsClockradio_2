package dk.dtu.philipsclockradio;

import static dk.dtu.philipsclockradio.ContextClockradio.ui;

public class StateSetPlayRadio extends StateAdapter{

    private ContextClockradio mContext;
    private int LEDnumber;
    private radioFrequency FMpreset1, FMpreset2, FMpreset3, FMpreset4, FMpreset5,
                           AMpreset1, AMpreset2, AMpreset3, AMpreset4, AMpreset5;

    @Override
    public void onEnterState(ContextClockradio context) {
        ui.toggleRadioPlaying("ON");
        ui.setDisplayText(context.getMradioFrequency().getModulation() +
                context.getMradioFrequency().getFrequency());
        initiatePresets();
        LEDnumber = 1;
    }

    @Override
    public void onExitState(ContextClockradio context) {
        turnOffAllLED(context);
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        context.setState(new StateSetPresets());
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {

        if(LEDnumber != 1){
            ui.turnOffLED(LEDnumber - 1);
        } else {
            ui.turnOffLED(5);
            ui.turnOffLED(1);

        }
        ui.turnOnLED(LEDnumber);
        if(!context.getUserPresets(LEDnumber).getModulation().equals(context.getMradioFrequency().getModulation())){
            context.getMradioFrequency().changeModulation();
        }
        context.getMradioFrequency().setFrequency(context.getUserPresets(LEDnumber).getFrequency());
        ui.setDisplayText(context.getMradioFrequency().getModulation() +
                context.getMradioFrequency().getFrequency());
        LEDnumber++;
        if(LEDnumber == 6){
            LEDnumber = 1;
        }



    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        context.getMradioFrequency().changeModulation();
        ui.setDisplayText(context.getMradioFrequency().getModulation() +
                context.getMradioFrequency().getFrequency());
        turnOffAllLED(context);
    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
    context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        if (context.getMradioFrequency().getModulation().equals("F")) {
            if (context.getMradioFrequency().getFrequency() == 88) {
                context.getMradioFrequency().setFrequency(108);
            } else {
                context.getMradioFrequency().setFrequency(context.getMradioFrequency().getFrequency() - 1);
            }
        } else {
            if (context.getMradioFrequency().getFrequency() == 54) {
                context.getMradioFrequency().setFrequency(160);
            } else {
                context.getMradioFrequency().setFrequency(context.getMradioFrequency().getFrequency() - 1);
            }
        }
        turnOffAllLED(context);
        ui.setDisplayText(context.getMradioFrequency().getModulation() +
                context.getMradioFrequency().getFrequency());
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        if (context.getMradioFrequency().getModulation().equals("F")) {
            if (context.getMradioFrequency().getFrequency() == 108) {
                context.getMradioFrequency().setFrequency(88);
            } else {
                context.getMradioFrequency().setFrequency(context.getMradioFrequency().getFrequency() + 1);
            }
        } else {
            if (context.getMradioFrequency().getFrequency() == 160) {
                context.getMradioFrequency().setFrequency(54);
            } else {
                context.getMradioFrequency().setFrequency(context.getMradioFrequency().getFrequency() + 1);
            }
        }
        ui.setDisplayText(context.getMradioFrequency().getModulation() +
                context.getMradioFrequency().getFrequency());
        turnOffAllLED(context);
    }

    @Override
    public void onLongClick_Hour(ContextClockradio context) {
        mContext = context;
        if (context.getMradioFrequency().getModulation().equals("F")) {
            if (mContext.getMradioFrequency().getFrequency() <= getPreset("F", 1).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("F", 5).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() <= getPreset("F", 2).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("F", 1).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() <= getPreset("F", 3).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("F", 2).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() <= getPreset("F", 4).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("F", 3).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() <= getPreset("F", 5).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("F", 4).getFrequency());
            }
        } else {
            if (mContext.getMradioFrequency().getFrequency() <= getPreset("A", 1).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("A", 5).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() <= getPreset("A", 2).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("A", 1).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() <= getPreset("A", 3).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("A", 2).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() <= getPreset("A", 4).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("A", 3).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() <= getPreset("A", 5).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("A", 4).getFrequency());
            }
        }
        turnOffAllLED(context);
        ui.setDisplayText(context.getMradioFrequency().getModulation() +
                context.getMradioFrequency().getFrequency());
    }

    @Override
    public void onLongClick_Min(ContextClockradio context) {
        mContext = context;
        if (context.getMradioFrequency().getModulation().equals("F")) {
            if (mContext.getMradioFrequency().getFrequency() >= getPreset("F", 5).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("F", 1).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() >= getPreset("F", 4).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("F", 5).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() >= getPreset("F", 3).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("F", 4).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() >= getPreset("F", 2).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("F", 3).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() >= getPreset("F", 1).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("F", 2).getFrequency());
            }
        } else {
            if (mContext.getMradioFrequency().getFrequency() >= getPreset("A", 5).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("A", 1).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() >= getPreset("A", 4).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("A", 5).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() >= getPreset("A", 3).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("A", 4).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() >= getPreset("A", 2).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("A", 3).getFrequency());
            } else if (mContext.getMradioFrequency().getFrequency() >= getPreset("A", 1).getFrequency()) {
                mContext.getMradioFrequency().setFrequency(getPreset("A", 2).getFrequency());
            }
        }
        turnOffAllLED(context);
        ui.setDisplayText(context.getMradioFrequency().getModulation() +
                context.getMradioFrequency().getFrequency());
    }

    public void turnOffAllLED(ContextClockradio context) {
        ui.turnOffLED(1);
        ui.turnOffLED(2);
        ui.turnOffLED(3);
        ui.turnOffLED(4);
        ui.turnOffLED(5);
    }


    public void initiatePresets() {
        FMpreset1 = new radioFrequency();
        FMpreset2 = new radioFrequency();
        FMpreset3 = new radioFrequency();
        FMpreset4 = new radioFrequency();
        FMpreset5 = new radioFrequency();
        AMpreset1 = new radioFrequency();
        AMpreset2 = new radioFrequency();
        AMpreset3 = new radioFrequency();
        AMpreset4 = new radioFrequency();
        AMpreset5 = new radioFrequency();

        FMpreset1.setFrequency(88);
        FMpreset2.setFrequency(90);
        FMpreset3.setFrequency(96);
        FMpreset4.setFrequency(100);
        FMpreset5.setFrequency(108);
        AMpreset1.changeModulation();
        AMpreset2.changeModulation();
        AMpreset3.changeModulation();
        AMpreset4.changeModulation();
        AMpreset5.changeModulation();
        AMpreset1.setFrequency(54);
        AMpreset2.setFrequency(82);
        AMpreset3.setFrequency(100);
        AMpreset4.setFrequency(145);
        AMpreset5.setFrequency(160);

    }

    public radioFrequency getPreset(String modulation, int preset) {
        radioFrequency r = new radioFrequency();
        if (modulation.equals("F")) {
            switch (preset) {
                case 1:
                    r = FMpreset1;
                    break;
                case 2:
                    r = FMpreset2;
                    break;
                case 3:
                    r = FMpreset3;
                    break;
                case 4:
                    r = FMpreset4;
                    break;
                case 5:
                    r = FMpreset5;
                    break;
                default:
                    break;
            }
        } else {
            switch (preset) {
                case 1:
                    r = AMpreset1;
                    break;
                case 2:
                    r = AMpreset2;
                    break;
                case 3:
                    r = AMpreset3;
                    break;
                case 4:
                    r = AMpreset4;
                    break;
                case 5:
                    r = AMpreset5;
                    break;
                default:
                    break;
            }
        }
        return r;
    }

}
