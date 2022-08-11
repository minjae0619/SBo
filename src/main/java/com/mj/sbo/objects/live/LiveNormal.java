package com.mj.sbo.objects.live;

public class LiveNormal implements Live {

    @Override
    public String getElementId(ElementId elementId) {
        return elementId.getNormal();
    }

    @Override
    public LiveType getLiveType() {
        return LiveType.NORMAL;
    }

}
