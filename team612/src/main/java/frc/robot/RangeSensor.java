package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class RangeSensor {
    private final int useless = 0x03, ultra_port = 0x3a, ultra_reg = 0x04, ir_reg = 0x05; // memory addresses and registers for the i2c-based ultrasonic and ir sensors 
    private final int ultra_deadband = 1; // distance deadband (in cm (probably))

    private I2C ultra;

    public RangeSensor(){
        ultra = new I2C(Port.kOnboard, (ultra_port >> 1) | 0x00);
        ultra.write(useless, 1); // initial write
    }

    private byte getUltraVal(){ // reads and returns raw values from ultrasonic sensor, negative iff sensor is not being read from
        byte [] ultra_read = new byte[1];
        if(ultra.read(ultra_reg, 1, ultra_read)){
        return ultra_read[0];
        }
        return -1;
    }

    private byte getIRVal(){ // reads and returns raw values from ultrasonic sensor, negative iff sensor is not being read from
        byte [] ir_read = new byte[1];
        if(ultra.read(ir_reg, 1, ir_read)){
        return ir_read[0];
        }
        return -1;
    }
    
    public boolean isIR(){
        return getIRVal() != 0 ? true : false;
    }
    public int getDistanceVal() { // switches between ultrasonic and ir values, depending on distance 
        byte[] ir_read = new byte[1];
        ultra.read(ir_reg, 1, ir_read);

        System.out.println(ultra.read(ir_reg, 1, ir_read));
        
        if (ir_read[0] != 0) {
            return getUltraVal();
        } else {
            return getIRVal();
        }
    }

    public int errorVal(int ultra_target){ // gets error between observed and expected distances for ultrasonic
        return Math.abs(getDistanceVal() - ultra_target);
    }
    public boolean inRange(int ultra_target){
        return errorVal(ultra_target) < ultra_deadband ? true: false;
    }

    public I2C getUltra(){
        return ultra;
    }

}
