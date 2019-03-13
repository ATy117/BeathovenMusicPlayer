package model_rework;

import view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class Model {
    private List<View> views = new ArrayList<>();

    public void Attach(View view)
    {
        views.add(view);
        Notify();
    }

    public void Detach(View view)
    {
        views.remove(view);
    }

    public void Notify()
    {
        for (View v: views)
        {
            v.Update();
        }
    }
}
