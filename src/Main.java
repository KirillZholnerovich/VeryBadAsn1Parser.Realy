import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        String certificate =
                "MIIDjDCCAnQCCQCQuY0GISuJODANBgkqhkiG9w0BAQsFADCBhzELMAkGA1UEBhMC" +
                "QlkxEDAOBgNVBAgMB0JlbGFydXMxDjAMBgNVBAcMBU1pbnNrMQ0wCwYDVQQKDARC" +
                "U1RVMQwwCgYDVQQLDANGSVQxEjAQBgNVBAMMCWdtYWlsLmNvbTElMCMGCSqGSIb3" +
                "DQEJARYWcGVrMTgxMi53b3JrQHlhbmRleC5ydTAeFw0xNzA2MjExOTU5NDJaFw0x" +
                "ODA2MjExOTU5NDJaMIGHMQswCQYDVQQGEwJCWTEQMA4GA1UECAwHQmVsYXJ1czEO" +
                "MAwGA1UEBwwFTWluc2sxDTALBgNVBAoMBEJTVFUxDDAKBgNVBAsMA0ZJVDESMBAG" +
                "A1UEAwwJZ21haWwuY29tMSUwIwYJKoZIhvcNAQkBFhZwZWsxODEyLndvcmtAeWFu" +
                "ZGV4LnJ1MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5NSd6KjJBtgg" +
                "OIEnwh3hg5nba27xic9V3wXL+KXHKNjqqR2ovzuWUVQXdL66FUP5F6IS+WHQmwlg" +
                "mn16++s1Ny/af7qlEr4u0ilfOGh/F6TTpB+7fgEtAUaNFw/DR/e1RQHHxlGElaRn" +
                "L9tqgTYL+R0dtYOq5oLTyR/0NpR9Ltgima/hj/W8FRerYIcOeuyDgMho+mgklxOw" +
                "kBCjQm+8ua0wWzb1KJkKlRl7TprODlPTrAVMYuOgkyCRVu5JvNp38RLuwbZWZBNc" +
                "02PYBrUjJEY+/m3OE2DSdsnaiyZlrRXaEKjMZHhoUqFudcK6jSed1kJacpiBZe8c" +
                "LBDK93HjPwIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQBMmre4HRBwDxswz0qbkxkJ" +
                "5y+IQUalPYyceJjn+7mY//06kwARmY1PtxDy2EhFkbxNmQgfyr1lF1rt17Rsgf9W" +
                "aUWl+TvwvFPUyAX+Y1OA8IUDlLZKq0BQC96crFjni8MRAJ2y4zYrLGsZXQZl5Mzo" +
                "3qP7JWCCkmVs9GWtlYYZhZeA4YiDuWv29fkiRBZrKOl+hPx+Zr5j4mU5ysW32MKM" +
                "Neb/bYMP4F/kk1B76ITSCrraRt+cPmU427C+qxggJwepBa5EdBd9LRfYTtoXD/2M" +
                "7PkbUgkeoCwH0eD4BVdsTVUrstqcHGmH3RJXSJHXSoYb9upToxmQ1k2FdD36afNo";

        byte[] bytes = Base64.getDecoder().decode(certificate);

        AsnCore asnCore = new AsnCore(bytes);
        asnCore.parse();
    }

    private String parseSequence(){
        return "";
    }
}

