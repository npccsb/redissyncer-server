/*
 * Copyright 2016-2018 Leon Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package syncer.replica.cmd.parser;



import syncer.replica.cmd.CommandParser;
import syncer.replica.cmd.CommandParsers;
import syncer.replica.cmd.impl.EvalCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon Chen
 * @since 2.1.0
 */
public class EvalParser implements CommandParser<EvalCommand> {
    @Override
    public EvalCommand parse(Object[] command) {
        int idx = 1;
        byte[] script = CommandParsers.toBytes(command[idx]);
        idx++;
        int numkeys = CommandParsers.toInt(command[idx++]);
        byte[][] keys = new byte[numkeys][];
        for (int i = 0; i < numkeys; i++) {
            keys[i] = CommandParsers.toBytes(command[idx]);
            idx++;
        }
        List<byte[]> list = new ArrayList<>();
        while (idx < command.length) {
            list.add(CommandParsers.toBytes(command[idx]));
            idx++;
        }
        byte[][] args = new byte[list.size()][];
        list.toArray(args);
        return new EvalCommand(script, numkeys, keys, args);
    }

}
