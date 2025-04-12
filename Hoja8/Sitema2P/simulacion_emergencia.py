import simpy
import random
from generador_pacientes import generar_pacientes

# Configuración inicial
RANDOM_SEED = 10
NUM_ENFERMERAS = 2
NUM_DOCTORES = 2
INTERVALO_LLEGADA = 5  # promedio de tiempo entre llegadas
TIEMPO_TRIAGE = 10

def triage(env, paciente, enfermeras, prioridad):
    with enfermeras.request(priority=prioridad) as req:
        yield req
        print(f"[{env.now}] Triage: {paciente}")
        yield env.timeout(TIEMPO_TRIAGE)

def proceso_paciente(env, nombre, severidad, enfermeras):
    yield env.timeout(random.expovariate(1.0 / INTERVALO_LLEGADA))
    yield env.process(triage(env, nombre, enfermeras, severidad))
    print(f"[{env.now}] {nombre} pasa a consulta con prioridad {severidad}")

def setup(env, enfermeras):
    pacientes = generar_pacientes()
    for nombre, severidad in pacientes:
        env.process(proceso_paciente(env, nombre, severidad, enfermeras))

if __name__ == '__main__':
    random.seed(RANDOM_SEED)
    env = simpy.Environment()
    enfermeras = simpy.PriorityResource(env, capacity=NUM_ENFERMERAS)
    env.process(setup(env, enfermeras))
    env.run(until=100)
