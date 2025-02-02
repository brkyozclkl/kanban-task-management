import customtkinter as ctk
from datetime import datetime, timedelta
import calendar
import json
import os
from tkcalendar import Calendar
from plyer import notification
import threading
import time
import win32com.client
import hashlib
from tkinter import filedialog
from PIL import Image, ImageTk
import shutil
import base64
import requests
from bs4 import BeautifulSoup
import re
import webbrowser
from tag_manager import TagManager
from language_manager import LanguageManager

class KanbanApp:
    def __init__(self):
        self.root = ctk.CTk()
        self.root.title("Kanban Task Manager")
        self.root.geometry("1200x800")
        
        # Initialize managers
        self.tag_manager = TagManager()
        self.language_manager = LanguageManager()
        
        # Create main label
        self.main_label = ctk.CTkLabel(self.root, text="Kanban Task Manager")
        self.main_label.pack(pady=20)
        
    def run(self):
        self.root.mainloop()

if __name__ == "__main__":
    app = KanbanApp()
    app.run() 