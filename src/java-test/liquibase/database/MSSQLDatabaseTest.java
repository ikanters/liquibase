package liquibase.database;

public class MSSQLDatabaseTest extends AbstractDatabaseTest {
    public MSSQLDatabaseTest() {
        super(new MSSQLDatabase());
    }

    public void testGetBlobType() {
        assertEquals("IMAGE", getDatabase().getBlobType());
    }

    public void testSupportsInitiallyDeferrableColumns() {
        assertFalse(getDatabase().supportsInitiallyDeferrableColumns());
    }

    public void testGetBooleanType() {
        assertEquals("BIT", getDatabase().getBooleanType());
    }

    public void testGetCurrencyType() {
        assertEquals("MONEY", getDatabase().getCurrencyType());
    }

    public void testGetUUIDType() {
        assertEquals("UNIQUEIDENTIFIER", getDatabase().getUUIDType());
    }

    public void testGetClobType() {
        assertEquals("TEXT", getDatabase().getClobType());
    }

    public void testGetDateType() {
        assertEquals("DATE", getDatabase().getDateType());
    }

    public void testGetDateTimeType() {
        assertEquals("DATETIME", getDatabase().getDateTimeType());
    }

    protected String getProductNameString() {
        return "Microsoft SQL Server";
    }

    public void testGetCurrentDateTimeFunction() {
        assertEquals("GETDATE()", getDatabase().getCurrentDateTimeFunction());
    }
}