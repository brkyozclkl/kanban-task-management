# 📋 Kanban Task Management System

<div align="center">


Modern ve kullanıcı dostu bir görev yönetim uygulaması | A modern and user-friendly task management application


</div>

> [!NOTE]  
> 🚧 **Geliştirme Aşaması | Development Status**  
> Bu proje aktif olarak geliştirme aşamasındadır. Özellikler ve dokümantasyon sürekli güncellenmektedir.  
> This project is currently under active development. Features and documentation are being continuously updated.

## 🚀 Özellikler

- 🔄 Sürükle-bırak görev yönetimi
- 👥 Görev paylaşımı ve kullanıcı yönetimi
- 📅 Windows Takvim entegrasyonu
- 🏷️ Görev etiketleri ve öncelik seviyeleri
- 🔔 Bildirim sistemi
- 📰 Gündem haberleri
- 📊 İstatistikler ve raporlama
- 📝 Not alma sistemi
- 🎨 Tema desteği (Açık/Koyu)

## ⚙️ Kurulum

1. Repoyu klonlayın:
```bash
git clone https://github.com/berkayozcelikel/kanban-task-management.git
cd kanban-task-management
```

2. Gerekli paketleri yükleyin:
```bash
pip install -r requirements.txt
```

3. Uygulamayı başlatın:
```bash
python kanban_gui.py
```

## 📖 Kullanım

1. Giriş yapın veya yeni hesap oluşturun
2. Görev eklemek için "Yeni Görev" butonunu kullanın
3. Görevleri sürükleyerek veya ok butonlarıyla taşıyın
4. Görevleri paylaşmak için paylaş butonunu kullanın
5. İstatistikleri ve raporları görüntüleyin
6. Notlar ekleyin ve yönetin

## 💻 Sistem Gereksinimleri

- Python 3.8+
- Windows işletim sistemi (Takvim entegrasyonu için)

## 📁 Proje Yapısı

```
kanban-task-management/
├── kanban_gui.py        # Ana uygulama dosyası
├── tag_manager.py       # Etiket yönetimi
├── language_manager.py  # Dil yönetimi
├── requirements.txt     # Gerekli paketler
└── data/
    ├── users.json          # Kullanıcı verileri
    ├── tag_colors.json     # Etiket renkleri
    └── shared_tasks.json   # Paylaşılan görevler
```

## 🛠️ Özelleştirme

- Etiket renkleri `data/tag_colors.json` dosyasından özelleştirilebilir
- Dil ayarları `language_manager.py` üzerinden değiştirilebilir

## 🔒 Güvenlik

- Kullanıcı şifreleri SHA-256 ile hashlenmiş olarak saklanır
- Görev paylaşımları kullanıcı bazlı yetkilendirme ile korunur

## 🤝 Katkıda Bulunma

1. Bu repoyu forklayın
2. Yeni bir branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Değişikliklerinizi commit edin (`git commit -m 'feat: Add amazing feature'`)
4. Branch'inizi push edin (`git push origin feature/amazing-feature`)
5. Pull Request oluşturun

## 📝 Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakınız.

## 📧 İletişim

Berkay ÖZÇELİKEL - [berkayozcelikel0@gmail.com](mailto:berkayozcelikel0@gmail.com)


---

<div align="center">
⭐️ Bu projeyi beğendiyseniz yıldız vermeyi unutmayın!
</div>
