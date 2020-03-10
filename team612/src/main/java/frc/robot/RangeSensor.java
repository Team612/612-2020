package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class RangeSensor {
    private final int useless = 0x03, ultra_port = 0x3a, ultra_reg = 0x04, ir_reg = 0x05; 
    private final int ultra_deadband = 150;

    private I2C ultra;

    public RangeSensor(){
        ultra = new I2C(Port.kOnboard, (ultra_port >> 1) | 0x00);
        ultra.write(useless, 1); // Initial write
    }

    public byte getUltra(){
        byte [] ultra_read = new byte[1];
        if(ultra.read(ultra_reg, 1, ultra_read)){
        return ultra_read[0];
        }
        return -1;
    }

    public byte getIR(){
        byte [] ir_read = new byte[1];
        if(ultra.read(ir_reg, 1, ir_read)){
        return ir_read[0];
        }
        return -1;
    }

    public double getDistance() {
        byte[] ir_read = new byte[1];
        ultra.read(ir_reg, 1, ir_read);

        System.out.println(ultra.read(ir_reg, 1, ir_read));
        
        if (ir_read[0] != 0) {
            return getUltra();
        } else {
            return getIR();
        }
    }

    public boolean inRange(){
        return getUltra() == ultra_deadband ? true: false;
    }

}
