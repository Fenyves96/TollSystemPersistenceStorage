import hu.fenyvesvolgyimate.tollsystem.dao.SqlLiteVignetteStorage;
import hu.fenyvesvolgyimate.tollsystem.entity.Vignette;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqlLiteVignetteStorageTests {
    @Test
    public void test(){
        SqlLiteVignetteStorage vignetteStorage = new SqlLiteVignetteStorage();
        vignetteStorage.initDatas();

        List<Vignette> vignetteList = vignetteStorage.findVignettesByRegistrationNumber("ABC-123");
        assertEquals(2 ,vignetteList.size());
    }
}
