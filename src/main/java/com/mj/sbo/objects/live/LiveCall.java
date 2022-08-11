package com.mj.sbo.objects.live;

public class LiveCall implements Live {



    @Override
    public String getElementId(ElementId elementId) {
        return elementId.getLiveCall();
    }

    @Override
    public LiveType getLiveType() {
        return LiveType.LIVE_CALL;
    }
}
