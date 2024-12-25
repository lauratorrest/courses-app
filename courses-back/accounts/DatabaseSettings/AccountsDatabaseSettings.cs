namespace accounts.DatabaseSettings
{
    public class AccountsDatabaseSettings
    {
        public string ConnectionString { get; set; } = null!;
        public string DatabaseName { get; set; } = null!;
        public string AccountsCollectionName { get; set; } = null!;
    }
}
