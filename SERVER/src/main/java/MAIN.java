public class MAIN {
    public static void main(String[] args) {
        CLOCK clock = new CLOCK();
        clock.start();
        DCMS_CLIENT dcms_client = new DCMS_CLIENT();
        dcms_client.start();
        DCMS_USER dcms_user = new DCMS_USER();
        dcms_user.start();
        DCMS_ADMIN dcms_admin = new DCMS_ADMIN();
        dcms_admin.start();

    }
}