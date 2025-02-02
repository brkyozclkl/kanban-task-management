class LanguageManager:
    def __init__(self):
        self.current_language = "tr"
        self.translations = {
            "tr": {
                "main.columns.todo": "Yapılacak",
                "main.columns.in_progress": "Devam Ediyor",
                "main.columns.completed": "Tamamlandı",
                "main.tags.manage": "Etiketleri Yönet",
                "main.tags.add_new": "Yeni Etiket Ekle",
                "main.tags.name": "Etiket Adı",
                "main.tags.color": "Renk",
                "main.tags.save": "Kaydet"
            },
            "en": {
                "main.columns.todo": "To Do",
                "main.columns.in_progress": "In Progress",
                "main.columns.completed": "Completed",
                "main.tags.manage": "Manage Tags",
                "main.tags.add_new": "Add New Tag",
                "main.tags.name": "Tag Name",
                "main.tags.color": "Color",
                "main.tags.save": "Save"
            }
        }
    
    def get_text(self, key):
        """Belirtilen anahtarın çevirisini getir"""
        return self.translations[self.current_language].get(key, key)
    
    def set_language(self, language):
        """Dili değiştir"""
        if language in self.translations:
            self.current_language = language
    
    def get_available_languages(self):
        """Mevcut dilleri getir"""
        return list(self.translations.keys()) 