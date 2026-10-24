import socket, struct, sys, time
def pkt(i, t, b):
    d = struct.pack('<ii', i, t) + b.encode() + b'\x00\x00'
    return struct.pack('<i', len(d)) + d
def recv(s):
    n = struct.unpack('<i', s.recv(4))[0]; d = b''
    while len(d) < n: d += s.recv(n - len(d))
    return d[8:-2].decode(errors='replace')
s = socket.create_connection(('127.0.0.1', 25702)); s.sendall(pkt(1, 3, 'noblanklinks')); recv(s)
for line in sys.stdin:
    line = line.strip()
    if not line or line.startswith('# '): continue
    if line.startswith('#sleep'): time.sleep(float(line.split()[1])); continue
    s.sendall(pkt(2, 2, line)); print('>', line, '=>', recv(s)[:4000], flush=True)
