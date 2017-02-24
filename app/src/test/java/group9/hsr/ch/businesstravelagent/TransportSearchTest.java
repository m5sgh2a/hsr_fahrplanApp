package group9.hsr.ch.businesstravelagent;

import org.junit.Test;

import android.os.Build;
import android.support.annotation.RequiresApi;
import group9.hsr.ch.businesstravelagent.Model.TransportSearchParameter;

import static org.junit.Assert.*;

@RequiresApi(api = Build.VERSION_CODES.N)
public class TransportSearchTest {
    private final String startLocation = "Buchs SG";
    private final String endLocation = "Zürich HB";
    private final String date = "2017/02/16";
    private final String time = "08:12:45";
    private final boolean isArrival = false;

    @Test
    public void SetSearchParameter_correctStartLocation() throws Exception {

        TransportSearchParameter para = GetSearchParamter();
        assertEquals(para.GetStartLocation(), startLocation);
    }

    @Test
    public void SetSearchParameter_correctEndLocation() throws Exception {

        TransportSearchParameter para = GetSearchParamter();
        assertEquals(para.GetEndLocation(), endLocation);
    }

    @Test
    public void SetSearchParameter_correctDate() throws Exception {

        TransportSearchParameter para = GetSearchParamter();
        assertEquals(para.GetDate(), date);
    }

    @Test
    public void SetSearchParameter_correctTime() throws Exception {

        TransportSearchParameter para = GetSearchParamter();
        assertEquals(para.GetTime(), time);
    }

    @Test
    public void SetSearchParameter_correctIsArrival() throws Exception {

        TransportSearchParameter para = GetSearchParamter();
        assertEquals(para.GetIsArrival(), isArrival);
    }


    private TransportSearchParameter GetSearchParamter() {

        return new TransportSearchParameter
                (
                        startLocation,
                        endLocation,
                        date,
                        time,
                        isArrival
                );
    }
}