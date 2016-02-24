#ifndef UTIL_H
#define UTIL_H

#include <cstdlib>
#include <fstream>
#include <iostream>
#include <stdint.h>
#include <string>
#include <limits>

#include "openG.h"

static bool safe_strtoull(const char **str, size_t &val) {
    const char *before = *str;
    char *after;

    val = strtoull(before, &after, 10);
    *str = after;

    return before < after;
}

static void skip_whitespace(const char **str) {
    while (isspace(**str)) {
        (*str)++;
    }
}

template <typename G>
bool write_graph_vertices(G &graph, const std::string &file) {
    typedef typename G::vertex_iterator vertex_iterator;

    std::ofstream f(file.c_str());

    if (!f) {
        std::cerr << "failed to open file: " << file << std::endl;
        return false;
    }

    for (vertex_iterator it = graph.vertices_begin(); it != graph.vertices_end(); it++) {
        f << it->id() << " " << it->property() << "\n";
    }

    f.close();

    if (f.bad()) {
        std::cerr << "error while writing to file: " << file << std::endl;
        return false;
    }

    return true;
}
template <typename G>
bool write_csr_graph_vertices(G &graph, const std::string &file, bool value_convert=false) {
    std::ofstream f(file.c_str());

    if (!f) {
        std::cerr << "failed to open file: " << file << std::endl;
        return false;
    }

    if (value_convert)
    {
        for (uint64_t vid=0;vid<graph.vertex_num();vid++)
        {
            uint64_t value = graph.csr_vertex_property(vid).output_value();
            f << graph.csr_external_id(vid) << " " << graph.csr_external_id(value) << "\n";
        }

    }
    else
    {
        for (uint64_t vid=0;vid<graph.vertex_num();vid++)
        {
            f << graph.csr_external_id(vid) << " " << graph.csr_vertex_property(vid) << "\n";
        }
    }
    f.close();

    if (f.bad()) {
        std::cerr << "error while writing to file: " << file << std::endl;
        return false;
    }

    return true;
}


#endif
