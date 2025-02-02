import json
import os

class TagManager:
    def __init__(self):
        self.colors_file = "tag_colors.json"
        self.load_colors()
    
    def load_colors(self):
        """Etiket renklerini yükle"""
        if os.path.exists(self.colors_file):
            try:
                with open(self.colors_file, 'r', encoding='utf-8') as f:
                    self.colors = json.load(f)["colors"]
            except Exception as e:
                print(f"Renk yükleme hatası: {str(e)}")
                self.init_default_colors()
        else:
            self.init_default_colors()
    
    def init_default_colors(self):
        """Varsayılan renkleri ayarla"""
        self.colors = {
            "default": "#808080",
            "önemli": "#FF0000",
            "acil": "#FF4500",
            "beklemede": "#FFA500",
            "toplantı": "#4169E1",
            "proje": "#32CD32",
            "araştırma": "#9370DB",
            "geliştirme": "#20B2AA",
            "test": "#FFD700",
            "dokümantasyon": "#DDA0DD"
        }
        self.save_colors()
    
    def save_colors(self):
        """Renkleri kaydet"""
        try:
            with open(self.colors_file, 'w', encoding='utf-8') as f:
                json.dump({"colors": self.colors}, f, ensure_ascii=False, indent=4)
        except Exception as e:
            print(f"Renk kaydetme hatası: {str(e)}")
    
    def get_tag_color(self, tag):
        """Etiket rengini getir"""
        tag = tag.lower()
        return self.colors.get(tag, self.colors["default"])
    
    def update_tag_color(self, tag, color):
        """Etiket rengini güncelle"""
        tag = tag.lower()
        self.colors[tag] = color
        self.save_colors()
    
    def get_all_tags(self):
        """Tüm etiketleri getir"""
        return self.colors 