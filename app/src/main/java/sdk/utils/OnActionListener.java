package sdk.utils;

/**
 * 请求回调接口
 * @author zhang
 *
 */
public interface OnActionListener {

	public void onActionSuccess(int actionId, String ret);

	public void onActionFailed(int actionId, int httpStatus);

	public void onActionException(int actionId, String exception);
}
