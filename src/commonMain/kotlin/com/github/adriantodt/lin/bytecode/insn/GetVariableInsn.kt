package com.github.adriantodt.lin.bytecode.insn

import com.github.adriantodt.lin.bytecode.utils.requireU24
import com.github.adriantodt.lin.bytecode.utils.writeU24
import okio.Buffer

public data class GetVariableInsn(val nameConst: Int) : Insn() {
    override fun serializeTo(buffer: Buffer) {
        buffer.writeByte(Opcode.GET_VARIABLE.ordinal)
            .writeU24(nameConst.requireU24("GetVariableInsn#nameConst"))
    }
}
