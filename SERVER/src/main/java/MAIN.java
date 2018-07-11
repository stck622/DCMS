public class MAIN {
    public static void main(String[] args) {
        DCMS_CLIENT dcms_client = new DCMS_CLIENT();
        dcms_client.start();
        DCMS_USER dcms_user = new DCMS_USER();
        dcms_user.start();
    }
}