import requests
import os

# ALL 32 TEAMS FROM FIFA WORLD CUP 2022 QATAR
teams = [
    ("Argentina", "ar"),
    ("Australia", "au"),
    ("Belgium", "be"),
    ("Brazil", "br"),
    ("Cameroon", "cm"),
    ("Canada", "ca"),
    ("Costa Rica", "cr"),
    ("Croatia", "hr"),
    ("Denmark", "dk"),
    ("Ecuador", "ec"),
    ("England", "gb-eng"),   # England uses 'gb-eng'
    ("France", "fr"),
    ("Germany", "de"),
    ("Ghana", "gh"),
    ("Iran", "ir"),
    ("Japan", "jp"),
    ("Mexico", "mx"),
    ("Morocco", "ma"),
    ("Netherlands", "nl"),
    ("Poland", "pl"),
    ("Portugal", "pt"),
    ("Qatar", "qa"),
    ("Saudi Arabia", "sa"),
    ("Senegal", "sn"),
    ("Serbia", "rs"),
    ("South Korea", "kr"),
    ("Spain", "es"),
    ("Switzerland", "ch"),
    ("Tunisia", "tn"),
    ("United States", "us"),
    ("Uruguay", "uy"),
    ("Wales", "gb-wls")     # Wales uses 'gb-wls'
]

# Create folder
os.makedirs("src/main/resources/static/images/flags", exist_ok=True)

print("Downloading 32 World Cup 2022 flags...")

for team_name, code in teams:
    url = f"https://flagcdn.com/w40/{code}.png"  # w40 = better quality
    try:
        response = requests.get(url, timeout=10)
        if response.status_code == 200:
            path = f"src/main/resources/static/images/flags/{team_name}.png"
            with open(path, "wb") as f:
                f.write(response.content)
            print(f"Downloaded: {team_name}.png")
        else:
            print(f"Failed ({response.status_code}): {team_name} ({code})")
    except Exception as e:
        print(f"Error {team_name}: {e}")

print("\nALL FLAGS DOWNLOADED!")
print("Put folder in: src/main/resources/static/images/flags/")