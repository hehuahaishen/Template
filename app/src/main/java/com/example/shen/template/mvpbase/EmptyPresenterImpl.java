package com.example.shen.template.mvpbase;

/**
 * author:  shen
 * date:
 */
public class EmptyPresenterImpl extends BasePresenter<EmptyView, EmptyModelImpl> {
    public EmptyPresenterImpl(EmptyView mvpView) {
        super(mvpView);
    }

    @Override
    protected EmptyModelImpl createModel() {
        return new EmptyModelImpl();
    }

}
