import random

nombres_base = ["Carlos", "Ana", "Luis", "Sofía", "Pedro", "María"]

def generar_pacientes(n=10):
    pacientes = []
    for _ in range(n):
        nombre = random.choice(nombres_base)
        severidad = random.randint(1, 5)  # 1 = más urgente, 5 = menos urgente
        pacientes.append((nombre, severidad))
    return pacientes
