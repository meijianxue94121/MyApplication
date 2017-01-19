package sdk.base;

/**
 * 请求回调接口
 * @author zhang
 *
 */
public interface OnActionListener {
    void onActionSuccess (int actionId, String ret);

    void onActionFailed (int actionId, int httpStatus);

    void onActionException (int actionId, String exception);
}
