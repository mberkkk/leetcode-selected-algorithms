# Integer to Roman - LeetCode 12

Bu soru özelinde adım adım nasıl bir çözüm geliştirdiğimi ve karşılaştığım zorlukları paylaşmak istiyorum.

## Amaç

Bu soruda verilen bir tam sayıyı (`num`), geçerli bir Roma rakamı gösterimine (`String`) dönüştürmemiz isteniyor. Dönüşüm sırasında subtractive formları (`IV`, `IX`, `XL` vb.) doğru şekilde ele almak en kritik nokta.

## Çözüm Süreci ve Karşılaştığım Engeller

Bu problemi çözerken aklıma gelen ilk yöntemden nihai çözüme nasıl ulaştığımı paylaşmak isterim.

### 1. Basamaklara Ayırma Fikri ve if-else Cehennemi

Başlangıçta sayıyı basamaklarına ayırıp her basamağı ayrı ayrı çevirmeyi düşündüm. Mantıklı bir başlangıç noktasıydı; örneğin `1994` için binler basamağı `1` yani `M`, yüzler basamağı `9` yani `CM`, onlar basamağı `9` yani `XC`, birler basamağı `4` yani `IV` şeklinde ilerlenebilirdi.

İlk basamak olan binler (`M`) oldukça düzgün gitti çünkü 1000'in subtractive formu yoktu. Ama diğer basamaklara geçince işler karmaşıklaştı. Her basamak için 0'dan 9'a kadar olası her değeri `if-else` ile ele almak gerekiyordu:

```java
// Sadece onlar basamağı için bile böyle bir blok yazmak gerekiyordu:
if (tens == 9) result += "XC";
else if (tens == 8) result += "LXXX";
else if (tens == 7) result += "LXX";
else if (tens == 6) result += "LX";
else if (tens == 5) result += "L";
else if (tens == 4) result += "XL";
else if (tens == 3) result += "XXX";
else if (tens == 2) result += "XX";
else if (tens == 1) result += "X";
```

Bunu birler, onlar, yüzler ve binler için ayrı ayrı yazmak demek onlarca satır tekrarcı ve hata yapmaya açık kod demekti.

* **Sorun:** Değer ne kadar büyük olursa o kadar uzun `if-else` zinciri gerekiyordu. Subtractive formlar (`IV`, `IX`, `XL`, `XC`, `CD`, `CM`) her basamakta ayrı birer özel durum olarak ele alınmak zorundaydı. Temiz ve ölçeklenebilir bir çözüm değildi.

### 2. Çözümün Kilit Noktası: TreeMap ve floorKey

Bu if-else yığınından kurtulmak için kendime şunu sordum: **"Bir sayıya eşit ya da ondan küçük olan en büyük değeri tek satırda bulabilir miyim?"** Aklıma `TreeMap`'in `floorKey` metodu geldi. Acaba bunu burada kullanabilir miydim?

Cevap evet oldu. `TreeMap`, anahtarlarını kendi içinde **sıralı** tuttuğu için tam olarak bu işi yapan `floorKey(num)` metodunu sunuyor.

> `treeMap.floorKey(num)` → `num` değerine eşit ya da ondan küçük olan **en büyük anahtarı** döndürür.

Bu sayede subtractive formları (`CM`, `CD`, `XC`, `XL`, `IX`, `IV`) haritaya birer anahtar olarak eklemek yeterliydi; `floorKey` her seferinde otomatik olarak doğru sembolü seçti.

### 3. Özyinelemeli Yapı

Büyük değerden başlayıp kalan sayıyı özyinelemeli olarak çözmek, kodu son derece sade tuttu. Her adımda:
1. `floorKey` ile en uygun değeri bul,
2. Karşılık gelen sembolü sonuca ekle,
3. Bu değeri sayıdan çıkar ve kalanla aynı işlemi tekrarla.

Listemiz `num = 1994` olsun:

| Adım | num  | floorKey(n) | Sembol | Kalan |
|------|------|-------------|--------|-------|
| 1    | 1994 | 1000        | M      | 994   |
| 2    | 994  | 900         | CM     | 94    |
| 3    | 94   | 90          | XC     | 4     |
| 4    | 4    | 4           | IV     | 0     |

Sonuç: **`MCMXCIV`** ✓

### 4. TreeMap Senkronizasyonu

TreeMap içindeki tüm değerlerin doğru girilmiş olması kritik. Özellikle subtractive formlar eksik bırakılırsa `floorKey` yanlış bir sembol seçer.

Örneğin `900` haritada yoksa `floorKey(994)` → `500` (`D`) döner ve sonuç `DCCCCXCIV` gibi saçma bir şeye dönüşür.

## Çözüm

```java
public String intToRoman(int num) {
    TreeMap<Integer, String> treeMap = new TreeMap<>();
    treeMap.put(1000, "M");
    treeMap.put(900, "CM");
    treeMap.put(500, "D");
    treeMap.put(400, "CD");
    treeMap.put(100, "C");
    treeMap.put(90,  "XC");
    treeMap.put(50,  "L");
    treeMap.put(40,  "XL");
    treeMap.put(10,  "X");
    treeMap.put(9,   "IX");
    treeMap.put(5,   "V");
    treeMap.put(4,   "IV");
    treeMap.put(1,   "I");

    int n = treeMap.floorKey(num);

    if (n == num) {
        return treeMap.get(num);
    }

    return treeMap.get(n) + intToRoman(num - n);
}
```
