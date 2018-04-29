package com.lpoot4g4.tw.Model;
import com.lpoot4g4.tw.Model.EntityModel;

public class CannonModel extends EntityModel
{

    private double size;
    private double rotation;

    public CannonModel(float x,float y, double rot)
    {
        super(x,y);

        rotation = rot;
    }
}
