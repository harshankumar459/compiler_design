# Lab 6: Construction of LL(1) Parsing Table (Simple Version)

non_terminals = ['E', 'R', 'T', 'Y', 'F']
terminals = ['+', '*', '(', ')', 'i', '$']

parsing_table = {
    ('E', '('): 'E → TR',
    ('E', 'i'): 'E → TR',

    ('R', '+'): 'R → +TR',
    ('R', ')'): 'R → ε',
    ('R', '$'): 'R → ε',

    ('T', '('): 'T → FY',
    ('T', 'i'): 'T → FY',

    ('Y', '*'): 'Y → *FY',
    ('Y', '+'): 'Y → ε',
    ('Y', ')'): 'Y → ε',
    ('Y', '$'): 'Y → ε',

    ('F', '('): 'F → (E)',
    ('F', 'i'): 'F → i'
}

print("LL(1) Parsing Table:\n")

for nt in non_terminals:
    for t in terminals:
        if (nt, t) in parsing_table:
            print(f"M[{nt}, {t}] = {parsing_table[(nt, t)]}")