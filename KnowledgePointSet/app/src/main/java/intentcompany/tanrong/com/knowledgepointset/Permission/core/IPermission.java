package intentcompany.tanrong.com.knowledgepointset.Permission.core;

public interface IPermission {
    /**
     * 已经授权
     */
    void ganted();
    /*
    取消授权
     */
    void cancled();
    /*
    被拒绝后 点击了不再提示
     */
    void denied();
}
