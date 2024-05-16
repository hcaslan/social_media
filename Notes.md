# NoSQL ve SQL farkı?
NoSQL ve SQL arasındaki temel fark, verileri saklama ve sorgulama yöntemlerinde yatmaktadır.

SQL (Structured Query Language), ilişkisel veritabanı sistemlerinde kullanılan bir sorgulama dilidir. SQL tabanlı veritabanları, ilişkisel tablolarda yapılandırılmış verileri depolar. Bu tür veritabanları, önceden tanımlanmış şemalara (veri yapısı) sahip olma eğilimindedir. SQL, verilerin saklanması, güncellenmesi ve sorgulanması için bir dizi standart komut seti sunar. Veritabanı yönetim sistemleri (RDBMS) genellikle SQL tabanlıdır ve örnek olarak MySQL, Oracle, Microsoft SQL Server ve PostgreSQL gibi popüler veritabanı sistemleri bulunmaktadır.

NoSQL (Not Only SQL), SQL tabanlı ilişkisel veritabanı sistemlerinin dışında kalan veritabanı yönetim sistemleri için kullanılan bir terimdir. NoSQL veritabanları, yapılandırılmış tablolardan ziyade daha esnek bir veri modeline sahiptir. NoSQL veritabanları genellikle dağıtık sistemlerde ölçeklenebilirlik ve performans avantajları sağlamak amacıyla tasarlanmıştır. NoSQL veritabanları, belge tabanlı veritabanları, anahtar-değer depolama, sütun tabanlı veritabanlar ve graf tabanlı veritabanlar gibi çeşitli veri modellerini destekleyebilir. Örneğin, MongoDB, Cassandra, Redis ve Neo4j gibi popüler NoSQL veritabanı sistemleri bulunmaktadır.

NoSQL ve SQL arasındaki diğer farklar şunlardır:

Veri Yapısı: SQL veritabanları, tablolar, sütunlar ve satırlarla yapılandırılmış bir şemaya sahiptir. Verilerin ilişkisel olarak düzenlenmesi gereklidir. NoSQL veritabanları ise daha esnek bir veri modeline sahiptir ve verileri belgelere, anahtar-değer çiftlerine, sütunlara veya graf yapısına benzer şekillerde saklayabilir.
Ölçeklenebilirlik: NoSQL veritabanları, genellikle yüksek trafikli ve büyük ölçekli dağıtık sistemlerde daha iyi ölçeklenebilirlik sunar. SQL veritabanları ise ölçeklenebilirlik konusunda bazı zorluklar yaşayabilir.
Sorgu Dili: SQL, ilişkisel veritabanlarına sorguları sormak için kullanılan standart bir dil sağlar. NoSQL veritabanları ise farklı veri modellerine göre farklı sorgu dilleri veya API'ler kullanabilir. Örneğin, MongoDB için sorgulama dilini kullanırken, Redis için anahtar-değer tabanlı komutlar kullanabilirsiniz.
ACID Uyumluluğu: SQL veritabanları genellikle ACID (Atomiklik, Tutarlılık, İzole Edilebilirlik, Dayanıklılık) özelliklerine sahiptir. Bu, veri bütünlüğünü sağlar ve işlemlerin güvenilirliğini garanti eder. NoSQL veritabanları, bazı durumlarda ACID uyumluluğunu terk ederek, ölçeklenebilirlik ve performans avantajları sağlar.
Kullanım Senaryoları: SQL veritabanları, karmaşık ilişkiler ve veriler arasındaki bağlantıları gerektiren iş uygulamaları için genellikle daha uygun olabilir. Örneğin, finans, muhasebe veya envanter yönetimi gibi alanlarda SQL veritabanları sıkça tercih edilir. NoSQL veritabanları ise büyük veri, oturum yönetimi, içerik yönetimi, gerçek zamanlı analiz veya sosyal ağ verileri gibi uygulamalar için daha uygun olabilir.
Sonuç olarak, SQL ve NoSQL veritabanları farklı veri saklama ve sorgulama yaklaşımları sunar. SQL, yapılandırılmış verilerin ilişkisel tablolarda depolanması ve SQL sorgularıyla erişilmesi için kullanılırken, NoSQL veritabanları daha esnek veri modelleri ve ölçeklenebilirlik avantajları sunar. Hangi veritabanı türünün tercih edileceği, uygulama ihtiyaçlarına, veri yapısına ve performans gereksinimlerine bağlı olarak değişebilir.

# NoSQL db tipleri nelerdir? Ve altındaki databaseler nelerdir?

NoSQL veritabanları, farklı veri modeli türlerini destekleyen birçok alt kategoriye ayrılabilir. İşte yaygın NoSQL veritabanı türleri ve altındaki bazı popüler veritabanı sistemleri:

1. Belge Tabanlı Veritabanlar:
    - MongoDB
    - Couchbase
    - CouchDB
    - RavenDB

2. Anahtar-Değer Depolama:
    - Redis
    - Riak
    - Amazon DynamoDB
    - Apache Cassandra (anahtar-sütun mağazası olarak da bilinir)

3. Sütun Tabanlı Veritabanlar:
    - Apache Cassandra
    - HBase
    - Amazon SimpleDB
    - ScyllaDB

4. Graf Tabanlı Veritabanlar:
    - Neo4j
    - Amazon Neptune
    - ArangoDB
    - JanusGraph

5. Zaman Serisi Veritabanları:
    - InfluxDB
    - Prometheus
    - TimescaleDB
    - OpenTSDB

6. Nesne Tabanlı Veritabanlar:
    - db4o
    - Versant Object Database
    - ObjectDB

7. XML Veritabanları:
    - BaseX
    - eXist-db
    - MarkLogic

# CAP 

CAP teoremi, dağıtık sistemlerde meydana gelebilecek ağ kesintileri ve bölünmeler gibi olayları ele almaktadır. Aşağıda CAP teoreminin üç bileşeni açıklanmaktadır:

Tutarlılık (Consistency): Tutarlılık, dağıtık sistemdeki tüm kullanıcılara ve istemcilere aynı anda ve aynı veri görüntüsünü sunma yeteneğidir. Bu, veri güncellemeleri yapıldığında, tüm kopyalardaki verilerin birbirine uyumlu olmasını ifade eder. Kısacası, herhangi bir veri okuma işlemi, en son güncellenmiş ve tutarlı bir veri görüntüsü sağlamalıdır.
Kullanılabilirlik (Availability): Kullanılabilirlik, dağıtık sistemdeki bir veritabanının sürekli olarak yanıt verme yeteneğidir. Kullanılabilirlik, herhangi bir istemcinin verilere erişebilme ve işlem yapabilme yeteneğini ifade eder. Bir sistemin yüksek kullanılabilirliği olduğunda, istemciler taleplerine hızlı bir şekilde yanıt alır ve sistem kesintilere karşı dayanıklıdır.
Bölüm Toleransı (Partition Tolerance): Bölüm toleransı, bir dağıtık sistemdeki ağ kesintileri veya bölünmeler gibi olaylara karşı dirençli olma yeteneğidir. Bölüm toleransı, sistemdeki bir veya daha fazla bileşenin arızalanması veya ağdaki iletişim problemleri gibi durumlarla başa çıkabilme yeteneğini ifade eder. Bölüm toleransı, dağıtık sistemdeki bir veya daha fazla bölgedeki arızaların sistem bütününde bir etkiye neden olmamasını sağlar.

# ACID ?

ACID (Atomicity, Consistency, Isolation, Durability), ilişkisel veritabanı sistemlerinde veri bütünlüğünü sağlamak için kullanılan bir dizi özelliği ifade eder. ACID, işlem veya işlemlerinin güvenli bir şekilde gerçekleşmesini ve veritabanının tutarlılığını korumasını amaçlar. İşte ACID'nin açılımı ve her bir özelliğin anlamı:

1. Atomicity (Atomiklik): Atomiklik, bir işlemin tüm veya hiç şeklinde gerçekleşmesini ifade eder. İşlemler birden fazla adımdan oluşabilir, ancak atomiklik ilkesi, bu adımların tamamının başarılı bir şekilde tamamlanması veya hiçbirinin gerçekleşmemesi gerektiğini belirtir. Bir işlem atomik olduğunda, veritabanındaki veriler tutarlı bir durumda kalır. Eğer bir adımda bir hata oluşursa, işlem geri alınır ve tüm değişiklikler geri alınır.

2. Consistency (Tutarlılık): Tutarlılık, veritabanının belirli bir işlemi başlatmadan önce ve sonra belirli bir kural kümesini korumasını ifade eder. İşlem, veritabanının tutarlı bir durumda kalmasını sağlamalıdır. Bu, veritabanında tanımlanan kısıtlamaların (örneğin, birincil anahtar veya yabancı anahtar kısıtlamaları) işlem sonrasında da geçerli olduğu anlamına gelir. Tutarlılık, veritabanındaki verilerin bütünlüğünü sağlamak için önemlidir.

3. Isolation (İzolasyon): İzolasyon, aynı anda gerçekleştirilen işlemlerin birbirlerinden bağımsız olarak çalışması gerektiğini ifade eder. İşlemler birbirlerinden etkilenmemeli ve bir işlem diğerinin yarattığı geçici durumu görmemelidir. İzolasyon, veritabanının aynı anda çalışan işlemlerden dolayı tutarsız bir duruma düşmesini önler.

4. Durability (Kalıcılık): Kalıcılık, bir işlem tamamlandığında ve işlem sonrası veritabanı durumu sabitlendiğinde, verilerin kalıcı bir şekilde saklanmasını ifade eder. Bu, veritabanının fiziksel ortamda korunması ve güç veya sistem arızaları gibi olaylardan sonra bile verilerin geri getirilebilir olması anlamına gelir. Kalıcılık, veri kaybını önlemek ve verilerin güvenli bir şekilde depolanmasını sağlamak için önemlidir.

ACID, veritabanı sistemlerinde güvenilirlik, veri bütünlüğü ve tutarlılık sağlamak için kullanılan temel bir prensiptir. ACID özellikleri, ilişkisel veritabanı sistemlerinde sıklıkla kullanılırken, NoSQL veritabanları gibi diğer veritabanı sistemleri bu özelliklerin tamamını sağlamayabilir veya esnekliği artırmak için bazı özelliklerden feragat edebilir.