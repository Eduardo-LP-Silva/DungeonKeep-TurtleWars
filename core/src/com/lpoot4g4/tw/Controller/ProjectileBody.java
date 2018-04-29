package com.lpoot4g4.tw.Controller;

import com.badlogic.gdx.physics.box2d.World;
import com.lpoot4g4.tw.Model.EntityModel;
import com.lpoot4g4.tw.Model.ProjectileModel;

public class ProjectileBody extends EntityBody
{
    public ProjectileBody(World w, ProjectileModel pm)
    {
        super(w, pm);
    }
}
